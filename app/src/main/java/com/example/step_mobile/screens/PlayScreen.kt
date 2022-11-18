package com.example.step_mobile

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.StopRed
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(

    viewModel: MainViewModel,
    navController: NavController,
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 15.dp
): String {
    //creamos las variables
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf((viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises[viewModel.uiState.currentExIdx].duration * 1000).toLong())
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / (viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises[viewModel.uiState.currentExIdx].duration * 1000).toFloat()
            if(currentTime<=0 && viewModel.uiState.currentExIdx < viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises.size -1){
                viewModel.uiState.currentExIdx++
                currentTime = (viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises[viewModel.uiState.currentExIdx].duration * 1000).toLong()
                isTimerRunning = true
            }else if(currentTime<=0 && viewModel.uiState.currentCycleIdx < viewModel.uiState.currentWorkout.size-1){
                viewModel.uiState.currentCycleIdx++
                viewModel.uiState.currentExIdx =0
                currentTime = (viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises[viewModel.uiState.currentExIdx].duration * 1000).toLong()
                isTimerRunning = true
            }else if(currentTime<=0){
                isTimerRunning = !isTimerRunning
                navController.navigate("review_screen"){
                    popUpTo("view_routine_screen")
                }
                return@LaunchedEffect
            }//TODO: confirmacion para empezar otro ciclo(mega opcional)

        }
    }
    Box(
        modifier = modifier
            .onSizeChanged {

                size = it
            },

        contentAlignment = Alignment.Center

        ) {

            //Timer
            Canvas(modifier = modifier) {
                // bar inactivo
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                )

                drawArc(
                    color = activeBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                // calculamo donde va el circulito
                val center = Offset(size.width / 2f, size.height / 2f)
                val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
                // este es el circulito q da vueltas
                drawPoints(
                    listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = activeBarColor,
                    strokeWidth = (strokeWidth * 1f).toPx(),
                    cap = StrokeCap.Round,
                )
            }

            // Timer
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.uiState.currentCycleIdx <= viewModel.uiState.currentWorkout.size - 1 &&
                viewModel.uiState.currentExIdx <= viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises.size - 1
            ) {

                Text(text = viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises[viewModel.uiState.currentExIdx].exercise.name,
                    fontSize = 30.sp
                )

            }

            Text(
                text = stringResource(R.string.time_left, (currentTime / 1000L).toString()),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp)
            )
                Icon(
                    if(isTimerRunning && currentTime > 0L) painterResource(R.drawable.ic_baseline_pause_circle_24)
                    else if(!isTimerRunning && currentTime >=0L) painterResource(R.drawable.ic_baseline_play_circle_24)
                    else  painterResource(R.drawable.fast_forward) ,
                    contentDescription = null,
                    tint = if(!isTimerRunning || currentTime <= 0L){ DarkBlue }else{ StopRed },
                    modifier = Modifier.size(60.dp).clickable {
                        if(currentTime <= 0L) {

                            while(viewModel.uiState.currentWorkout[viewModel.uiState.currentCycleIdx].exercises.isEmpty() && viewModel.uiState.currentCycleIdx < viewModel.uiState.currentWorkout.size-1){
                                viewModel.uiState.currentCycleIdx++
                            }
                            if (viewModel.uiState.currentCycleIdx < viewModel.uiState.currentWorkout.size-1){
                                navController.navigate("search_screen")
                                return@clickable
                            }
                            viewModel.uiState.currentExIdx =0
                            viewModel.uiState.currentCycleIdx =0
                            isTimerRunning = true
                        } else {
                            isTimerRunning = !isTimerRunning
                        }
                    }
                )
        }


        }
    return  viewModel.uiState.currentCycles[viewModel.uiState.currentCycleIdx].name
}

@Composable
fun PlayScreen(myNavController: NavController, viewModel : MainViewModel) {
    Surface(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Column(verticalArrangement = Arrangement.Top) {
            if(viewModel.uiState.currentRoutine != null){
                ScreenTitle(title = viewModel.uiState.currentRoutine!!.name, showArrow = true, navController =  myNavController)
            }
        }
       Box {

            val contentPaddingModifier = Modifier.padding(16.dp)
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        mainContent(viewModel, myNavController)
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {


                        mainContent(viewModel, myNavController)


                    }
                }
            }
      }
    }
}
@Composable
fun mainContent( viewModel : MainViewModel, navController: NavController,) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        var cycleName by remember{
            mutableStateOf("")
        }

        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
                .width(300.dp)
        ) {
            Text(fontSize = 25.sp,
                text = stringResource(R.string.cycle, cycleName),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center)
        }

        Card(
            shape = RoundedCornerShape(100),
            modifier = Modifier.padding(5.dp)
        ) {

            cycleName =
                Timer(
                totalTime = 10L * 1000L,
                navController = navController,
                handleColor = Color.Green,//TODO: habria que pasarle bien los colores q no se muy bien como se hace
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFF37B900),
                modifier = Modifier.size(300.dp),
                viewModel = viewModel)

        }



        Button(
            onClick = {
                navController.navigate("play_screenNT")
            },
            modifier = Modifier
                .width(250.dp)
                .height(80.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = DarkBlue,
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(40.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 5.dp,
                pressedElevation = 8.dp
            )
        ) {
            Text(
                "To Step Workout",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )
        }
    }
}
