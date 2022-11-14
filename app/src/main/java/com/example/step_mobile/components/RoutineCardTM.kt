package com.example.step_mobile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.step_mobile.data.model.Routine

@Composable
fun RoutineCardTM(routine: Routine) {
    val paddingModifier = Modifier.padding(10.dp)
    //val paddingLeft = Modifier.padding(start =  10.dp).padding(5.dp)

    Card(shape = RoundedCornerShape(20.dp),elevation = 10.dp, modifier = paddingModifier) {
        //Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        //{
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            // Card(shape = RoundedCornerShape(10.dp),elevation = 5.dp, modifier = paddingModifier) {
            Text(text = routine.title, modifier = paddingModifier)
            //}
            // Card(shape = RoundedCornerShape(10.dp), elevation = 5.dp){
            Text(text = routine.description, modifier = paddingModifier)
            // }
        }
    }
    // }
}