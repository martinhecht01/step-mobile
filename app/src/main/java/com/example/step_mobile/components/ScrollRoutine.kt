package com.example.step_mobile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.step_mobile.data.model.Routine


@Composable
fun ScrollRoutine(routines: List<Routine>){


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
//                Box(modifier = Modifier.clickable { routine.onRoutineClicked(routine.id) }){
                    RoutineCard(routines[idx].name, routines[idx].detail, true, routines[idx].id)
//                }
            }
        }
    )
}
