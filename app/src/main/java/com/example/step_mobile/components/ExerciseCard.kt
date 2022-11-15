package com.example.step_mobile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.step_mobile.data.model.Exercise

@Composable
fun ExerciseCard(exercise : Exercise, Reps: Int) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(shape = RoundedCornerShape(20.dp),elevation = 10.dp, modifier = paddingModifier) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = exercise.name, modifier = paddingModifier)
            Text(text = exercise.detail, modifier = paddingModifier)
            Text( text = Reps.toString(), modifier = paddingModifier)
        }
    }
}