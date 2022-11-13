package com.example.step_mobile

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.step_mobile.classes.RoutineViewModel
import com.example.step_mobile.components.ScreenTitle

@Composable
fun SearchScreen(routineViewModel: RoutineViewModel, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize() ){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle(stringResource(R.string.search))
            SearchBar(navController = navController)
            Row(verticalAlignment = Alignment.Top) {
                orderDropdown()
                switchOrder()
            }
            ScrollRoutine(routineViewModel)
        }
    }
}

//check string with regex and return true if it is view_routine_screen/ and a number
fun isRoutineUrl(string: String): Boolean {
    val regex = Regex("view_routine_screen/[0-9]+")
    return regex.matches(string)
}

@Composable
fun SearchBar(navController: NavController){
    var url by rememberSaveable { mutableStateOf("") }
    var errorFlag by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 15.dp)) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            OutlinedTextField(
                value = url,
                textStyle = TextStyle(color = Color.DarkGray),
                onValueChange = { url = it },
                label = { Text(stringResource(id = R.string.search_url)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                isError = errorFlag,
            )
            OutlinedButton(onClick = {
                if(isRoutineUrl(url)){
                    errorFlag = false
                    navController.navigate(url)
                } else{
                    errorFlag = true
                }},
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 5.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = Color.DarkGray)
            }
        }
    }

}

@Composable
fun switchOrder(){
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(start = 10.dp, top = 25.dp, bottom = 25.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
    ){
        Column() {
            var order by remember { mutableStateOf(false) }
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


//TODO no queda claro que se puede clickear
@Composable
fun orderDropdown(){
    var expanded by remember { mutableStateOf(false) }

    //TODO cuidado con este string, puede llegar a dar problemas de sincronizacion
    var dateString = stringResource(R.string.date)

    var title by remember { mutableStateOf(dateString) }
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
                    fontSize = 25.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(5.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)),

            ) {
                DropdownMenuItem(onClick = { title = "Date"; expanded = false }) {
                    Text(text = stringResource(R.string.date))
                }
                DropdownMenuItem(onClick = { title = "Difficulty"; expanded = false }) {
                    Text(text = stringResource(R.string.difficulty))
                }
                DropdownMenuItem(onClick = { title = "Rating"; expanded = false }) {
                    Text(text = stringResource(R.string.rating))
                }
            }
        }
    }
}
