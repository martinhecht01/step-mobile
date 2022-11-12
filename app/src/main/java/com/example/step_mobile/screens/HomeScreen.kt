package com.example.step_mobile

import RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.step_mobile.classes.Routine
import com.example.step_mobile.components.ScreenTitle
import java.lang.Float

@Composable
fun HomeScreen() {
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
            TopWorkoutCard();
        }
    }

}

@Composable
fun TopWorkoutDetail(routine: Routine){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 5.dp)) {
        
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
fun TopWorkoutCard(){
    Card(modifier = Modifier
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(30.dp))
        .background(Color.White)
        .fillMaxWidth()
        .height(300.dp)) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                stringResource(R.string.top_workout),
                color = Color.DarkGray,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.
                padding(vertical = 10.dp)
            )
            RatingBar(rating = 4.5)
        }
    }
}
