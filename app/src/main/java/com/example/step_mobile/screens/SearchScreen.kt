package com.example.step_mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.components.ScrollRoutine
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.step_mobile.components.ScreenTitle
import kotlin.math.exp

@Preview
@Composable
fun SearchScreen() {
    Surface(modifier = Modifier.fillMaxSize() ){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle(title = "Search")
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 5.dp)) {
                orderDropdown()
                switchOrder()
            }
            ScrollRoutine()
        }
    }
}

@Composable
fun switchOrder(){
    Box(){
        Column(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))) {
            var order by remember { mutableStateOf(false) }
            OutlinedButton(onClick = {
                order = !order
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.DarkGray)) {
                if(order) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null, Modifier.size(30.dp))
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null, Modifier.size(15.dp))
                } else{
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null, Modifier.size(30.dp))
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null, Modifier.size(15.dp))
                }
            }
        }
    }

}

@Composable
fun orderDropdown(){
    var expanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("Date") }
    Box(modifier = Modifier
        .padding(25.dp)
        .clip(shape = RoundedCornerShape(10.dp))) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .width(120.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 25.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(5.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)),

            ) {
                DropdownMenuItem(onClick = { title = "Date"; expanded = false }) {
                    Text(text = "Date")
                }
                DropdownMenuItem(onClick = { title = "Difficulty"; expanded = false }) {
                    Text(text = "Difficulty")
                }
                DropdownMenuItem(onClick = { title = "Rating"; expanded = false }) {
                    Text(text = "Rating")
                }
            }
        }
    }
}
