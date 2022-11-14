package com.example.step_mobile

import RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.util.getViewModelFactory

@Composable
fun HomeScreen(mainViewModel: MainViewModel ) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Column(verticalArrangement = Arrangement.Top) {
            ScreenTitle(stringResource(R.string.home))
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            WelcomeCard("Pedro")
            TopWorkoutCard(viewModel(factory = getViewModelFactory()));
        }
    }

}

@Composable
fun SubtitleText(text: String){
    Text(text, fontSize = 20.sp, modifier = Modifier.padding(top = 15.dp), color = Color.Black)
}

@Composable
fun WelcomeCard(name: String){
    Card(modifier = Modifier
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(30.dp))
        .background(Color.White)
        .fillMaxWidth()
        .height(200.dp)) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
            Text(stringResource(R.string.welcome_message_with_name, name),
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp )
            SubtitleText(stringResource(id = R.string.cheering_message_1))
            SubtitleText(stringResource(id = R.string.cheering_message_2))
        }
    }
}

@Composable
fun TopWorkoutCard(mainViewModel: MainViewModel){
    var routine = Routine(
        name = "Hombros de acero",
        detail = "Tebi chupado",
        category = null,
        id = 100,
        date = null,
        score = 5,
        isPublic = true,
        difficulty = "Modo tobi",
        user = null,
        metadata = null
    )
    Text(
        stringResource(R.string.top_workout),
        color = Color.DarkGray,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
    Card(modifier = Modifier
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(30.dp))
        .background(Color.White)
        .fillMaxWidth()
        .height(300.dp)) {
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
            Image(painter = painterResource(R.drawable.trainers), contentDescription = null, contentScale = ContentScale.FillBounds, modifier = Modifier
                .height(200.dp)
                .width(200.dp))
        }

        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start, modifier = Modifier
            .clickable { /*TODO*/ }
            .padding(horizontal = 35.dp, vertical = 10.dp)){
            Text(
                text = routine.name,
                color = Color.DarkGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = routine.detail,
                color = Color.DarkGray,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            RatingBar(rating = 5.0, modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}
