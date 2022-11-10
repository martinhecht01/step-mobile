package com.example.step_mobile.screens

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.step_mobile.R
import com.example.step_mobile.components.ExerciseCard
import com.example.step_mobile.components.ExerciseCardTM
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.ui.theme.Purple200
import com.example.step_mobile.ui.theme.StepmobileTheme
import kotlin.math.round

@Composable
@Preview
fun ViewRoutine(routineId: Int) {
    Image(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.fondonp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StepmobileTheme() {
                Text(text = "Routine info", Modifier.padding(top = 10.dp))
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