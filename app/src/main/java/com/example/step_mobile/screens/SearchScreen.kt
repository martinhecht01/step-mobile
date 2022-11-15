package com.example.step_mobile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.components.ScrollRoutine
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenLoader
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SearchScreen( navController: NavController,  mainViewModel : MainViewModel) {
    val state = mainViewModel.uiState

    var expanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var order by remember { mutableStateOf(false) }

    title = stringResource(R.string.date)
    Surface(modifier = Modifier.fillMaxSize() ){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            ScreenTitle(stringResource(R.string.search))
            Row(verticalAlignment = Alignment.Top) {
                Card(modifier = Modifier
                    .padding(25.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable { },
                    elevation = 10.dp
                )
                {
                    Column(
                        modifier = Modifier
                            .background(color = Color.White)
                            .width(120.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = true },
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = title,
                                fontSize = (20 / LocalDensity.current.fontScale).sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)),

                            ) {
                            var dateString = stringResource(R.string.date)
                            DropdownMenuItem(onClick = { title = dateString; expanded = false }) {
                                Text(text = stringResource(R.string.date))
                            }

                            var nameString = "Name"
                            DropdownMenuItem(onClick = { title = nameString; expanded = false }) {
                                Text(text = nameString)
                            }
                            var detailsString = "Details"
                            DropdownMenuItem(onClick = { title = detailsString; expanded = false }) {
                                Text(text = detailsString)
                            }
                        }
                    }
                }
                Card(
                    elevation = 10.dp,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 25.dp, bottom = 25.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { }
                ){
                    Column() {
                        OutlinedButton(onClick = {
                            order = !order
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.DarkGray)) {
                            if(order) {
                                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null, Modifier.size(30.dp))
                                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null, Modifier.size(15.dp))
                            } else{
                                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null, Modifier.size(30.dp))
                                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null, Modifier.size(15.dp))
                            }
                        }
                    }
                }
            }
            if(state.isFetching){
                ScreenLoader()
            } else{
                ScrollRoutine(navController, state.routines, mainViewModel, title, order)
            }
        }
    }
}


