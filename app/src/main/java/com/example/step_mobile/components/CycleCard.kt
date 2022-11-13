package com.example.step_mobile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.step_mobile.ui.theme.DarkBlue

@Composable
fun CycleCard(title: String) {
    val paddingModifier = Modifier.padding(10.dp)
    //val paddingLeft = Modifier.padding(start =  10.dp).padding(5.dp)

Card(shape = RoundedCornerShape(20.dp),elevation = 10.dp, modifier = paddingModifier, backgroundColor = DarkBlue) { //TODO: cambiar color
        //Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        //{
        Row(modifier = Modifier.width(10.dp), verticalAlignment =  Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            // Card(shape = RoundedCornerShape(10.dp),elevation = 5.dp, modifier = paddingModifier) {
            Text(text = title, modifier = paddingModifier, fontWeight = FontWeight.Bold, color = Color.White)
            //}
            // Card(shape = RoundedCornerShape(10.dp), elevation = 5.dp){
            // }
        }
    }
    // }
}