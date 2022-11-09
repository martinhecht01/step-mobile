package com.example.step_mobile.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


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