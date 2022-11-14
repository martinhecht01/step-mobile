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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.SubtitleText
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.R.*
import com.example.step_mobile.classes.MainViewModel


@Composable
fun ScrollRoutine(routines: List<Routine>){
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
//             Box(modifier = Modifier.clickable { routine.onRoutineClicked(routine.id) }){
                    RoutineCard(routines[idx].name, routines[idx].detail, true, routines[idx].id)
//             }
                }

            }
        )
    }
}
