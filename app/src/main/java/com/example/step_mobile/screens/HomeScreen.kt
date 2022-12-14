package com.example.step_mobile

import StarBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenLoader
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.data.model.User
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.launch
@Composable
fun HomeScreen(navController: NavController, mainViewModel: MainViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondonp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        if (mainViewModel.uiState.isFetching) {
            ScreenLoader()
        } else {
            var user = mainViewModel.uiState.currentUser
            var name by remember { mutableStateOf("") }
            if (user != null) {
                name = user.firstName
            }
            Column(verticalArrangement = Arrangement.Top) {
                ScreenTitle(stringResource(R.string.home), false, navController)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WelcomeCard(name)
                TopWorkoutCard(navController, mainViewModel);
            }
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
fun TopWorkoutCard(navController: NavController, mainViewModel: MainViewModel){
    var scope = rememberCoroutineScope()
    if(!mainViewModel.uiState.isFetching) {

        Card(
            modifier = Modifier
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .background(Color.White)
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
                Image(
                    painter = painterResource(R.drawable.trainers),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                )
            }
            var routine = mainViewModel.getTopRoutine()
            if(routine != null) {
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                mainViewModel
                                    .getRoutine(routine.id)
                                    .invokeOnCompletion {
                                        mainViewModel.getFullCyclesExercises(routine.id)
                                    }
                            }
                            navController.navigate("view_routine_screen")
                        }
                        .padding(horizontal = 35.dp, vertical = 10.dp)) {
                    Text(
                        stringResource(R.string.top_workout),
                        color = DarkBlue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 10.dp)
                    )


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
                    StarBar(
                        rating = (routine.score ?: 0).toDouble() / 2,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            } else{
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(horizontal = 35.dp, vertical = 10.dp)) {
                    Text(text = stringResource(id = R.string.no_routines_home),
                        color = Color.DarkGray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}
