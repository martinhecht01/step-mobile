package com.example.step_mobile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.components.ExerciseCard

@Composable
fun PlayScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.play_screen),
            fontSize = 30.sp
        )
        CircularProgressIndicator(
                progress = 0.45f,
                color = Color.Red,
                modifier = Modifier.then(Modifier.size(300.dp)),
                strokeWidth = 20.dp
        )
        ExerciseCard("Bench Press", "Gayba Bodybuilder",15)


    }
}