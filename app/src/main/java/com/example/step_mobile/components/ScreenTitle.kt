package com.example.step_mobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


@Composable
fun ScreenTitle(title: String, showArrow: Boolean, navController : NavController){
    Card(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .height(70.dp), elevation = 5.dp){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
            if(!showArrow)
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.padding(all = 10.dp))
            else{
                Icon(imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }.padding(all = 10.dp).size(40.dp)
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        }
    }
}