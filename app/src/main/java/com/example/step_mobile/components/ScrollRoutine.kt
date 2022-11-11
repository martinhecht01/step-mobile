package com.example.step_mobile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.step_mobile.classes.RoutineViewModel


@Composable
fun ScrollRoutine(){
    val list = (1..10).map { it.toString() }

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
            items(list.size) { index ->
                RoutineCard("Rutina", "Descripcion", true)
            }
        }
    )
}
@Composable
fun ScrollRoutine2(routineViewModel: RoutineViewModel){
    val state = routineViewModel.state
    if(state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    LazyColumn(
        //minimo width que tiene que tener una columna
        modifier = Modifier.fillMaxWidth()
    ){
        items(state.routines){
            Column(modifier = Modifier.fillMaxWidth()){
                RoutineCardTM(it)
            }
        }
    }
}