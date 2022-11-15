package com.example.step_mobile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.SubtitleText
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.R.*
import com.example.step_mobile.classes.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ScrollRoutine(navController: NavController, noOrderRoutines: List<Routine>, mainViewModel: MainViewModel, order: String, reversed: Boolean){
    var routines by remember { mutableStateOf(noOrderRoutines) }

    when(order){
            "Date" -> routines = noOrderRoutines.sortedBy { it.date }
            "Name" -> routines = noOrderRoutines.sortedBy { it.name }
            "Details" -> routines = noOrderRoutines.sortedBy { it.detail }
    }
    if(reversed)
        routines = routines.reversed();

    Log.d("scroll", routines.size.toString())
    if(routines.size == 0){
        Card(modifier = Modifier
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(Color.White)
            .fillMaxWidth()
            .height(200.dp)) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = "Ups! Seems like you don't have any routines available!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 30.sp )
            }
        }
    } else {
        var scope = rememberCoroutineScope()
        LazyVerticalGrid(
            //minimo width que tiene que tener una columna
            columns = GridCells.Adaptive(200.dp),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(routines.size) { idx ->
                 Box(modifier = Modifier.clickable {
                     scope.launch {
                         mainViewModel.getRoutine(routines[idx].id)
                     }
                     navController.navigate("view_routine_screen")
                 }){
                        var isFav = mainViewModel.isFavourite(routines[idx].id)
                        RoutineCard(routines[idx].name, routines[idx].detail, routines[idx].score ?: 0 , isFav, routines[idx].id, mainViewModel)
                 }
                }

            }
        )
    }
}
