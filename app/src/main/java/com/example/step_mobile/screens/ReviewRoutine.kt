package com.example.step_mobile.screens

import StarBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenLoader
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.LightBlue
import com.example.step_mobile.ui.theme.StepmobileTheme
import kotlinx.coroutines.launch

@Composable
fun ReviewRoutine(navController: NavController, mainViewModel: MainViewModel){
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.fondonp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    StepmobileTheme() {
        ScreenTitle("", true, navController)
    }
    if(mainViewModel.uiState.isFetching){
        ScreenLoader()
    } else{
        if(mainViewModel.uiState.currentRoutine != null) {
            val routine = mainViewModel.uiState.currentRoutine!!
            var scope = rememberCoroutineScope()
            StepmobileTheme() {
                ScreenTitle(title = stringResource(id = R.string.review_routine), true, navController)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                var rating by remember { mutableStateOf(5.0) }
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .height(250.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 10.dp,
                    ) {
                    Text(text = routine.name.toUpperCase(), fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(), textAlign = TextAlign.Center )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(
                            onClick = { if (rating > 0.0) rating -= 0.5 },
                            colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue, contentColor = Color.White),
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .clip(RoundedCornerShape(15.dp))
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_remove_24),
                                contentDescription = "decrease rating"
                            )
                        }
                        StarBar(rating = rating)
                        Button(
                            onClick = { if (rating < 5.0) rating += 0.5 },
                            colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue, contentColor = Color.White),
                            modifier = Modifier.padding(horizontal = 15.dp)
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Increase rating")
                        }
                    }
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom){
                        Button(
                            onClick = { scope.launch {
                                navController.navigate("home_screen")
                                mainViewModel.reviewRoutine(Review((rating*2).toInt(), ""), routine.id)
                            } },
                            colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue, contentColor = Color.White),
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 15.dp)
                                .height(60.dp)
                                .clip(RoundedCornerShape(15.dp))
                        ) {
                            Text(text = stringResource(id = R.string.submit_review), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }

}