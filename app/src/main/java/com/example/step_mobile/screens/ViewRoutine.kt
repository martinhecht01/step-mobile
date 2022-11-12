package com.example.step_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.RoutineViewModel
import com.example.step_mobile.components.ExerciseCardTM
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.ui.theme.StepmobileTheme

@Composable
fun ViewRoutine(navController: NavController, id: Int, routineViewModel: RoutineViewModel) {
    val index = routineViewModel.getIndexWithId(id)
    var routine = routineViewModel.state.routines[index]
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.fondonp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StepmobileTheme() {
                ScreenTitle(title = routine.title)
            }

            Card(
                modifier = Modifier.padding(20.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 10.dp,

            ) {
                RoutineInfo()
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(alignment = Alignment.Bottom)
                        .padding(20.dp)
                        .size(60.dp),
                    shape = RoundedCornerShape(100),
                    elevation = ButtonDefaults.elevation(5.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = PlayGreen, contentColor = Color.White)) {
                   Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                }
            }
        }


}

@Composable
fun RoutineInfo(){
    val paddingModifier = Modifier.padding(10.dp)
    val list = (1..9).map { it.toString() }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(200.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(list.size) { index ->
                        ExerciseCardTM("Ejercicio", 5)
                    }
                }
            )
        }
}