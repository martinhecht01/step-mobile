package com.example.step_mobile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.search_screen),
            fontSize = 30.sp
        )
    }

}

// lazy vertical grid with one column
//@Composable
//fun ScrollRoutines(){
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(1),
//        contentPadding = PaddingValues(16.dp),
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(100) {
//            Text("Item $it")
//        }
//    }
//
//}
