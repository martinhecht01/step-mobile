package com.example.step_mobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.R


@Composable
fun ScreenTitle(title: String){
    Card(modifier = Modifier.background(Color.White).fillMaxWidth().height(70.dp), elevation = 5.dp){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.padding(all = 10.dp))

        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        }
    }
}