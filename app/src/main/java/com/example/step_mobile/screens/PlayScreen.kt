package com.example.step_mobile

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.components.ExerciseCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.example.step_mobile.data.model.Exercise
import com.example.step_mobile.classes.PlayViewModel
import com.example.step_mobile.data.model.Routine

import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(

    viewModel: PlayViewModel,

    totalTime: Long,


    handleColor: Color,


    inactiveBarColor: Color,


    activeBarColor: Color,
    modifier: Modifier = Modifier,


    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    //creamos las variables
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
            if(currentTime<=0 && viewModel.currentIndex() != 1){//TODO:esto esta mal adaptar a cada rutina



                currentTime = totalTime
                isTimerRunning = true
                viewModel.incIndex()
            }else if(currentTime<=0 && viewModel.currentIndex() == 1){
                isTimerRunning = !isTimerRunning
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        // draw the timer
        Canvas(modifier = modifier) {
            // bar inactivo
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),


                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
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
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }
        // aca le pasas el tiempo papurri
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        // crea el boton paa
        Button(
            onClick = {
                if(currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                    viewModel.incIndex()
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        ) {
            Text(
                text = if (isTimerRunning && currentTime > 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Next"
            )
        }
    }
}

@Composable
fun PlayScreen(routine: Routine, viewModel : PlayViewModel) {
    Surface(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
//        val iterator = routine.exercises.listIterator() TODO: las routines ahora tienen cycles
        Box {
            val contentPaddingModifier = Modifier.padding(16.dp)
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        mainContent(routine, viewModel)
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        mainContent(routine, viewModel)


                    }
                }
            }
        }
    }
}
@Composable
fun mainContent(routine: Routine, viewModel : PlayViewModel) {


    Text(
        text = stringResource(id = R.string.play_screen),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Box(
        contentAlignment = Alignment.Center
    ) {
        Timer(
            totalTime = 10L * 1000L,
            handleColor = Color.Green,//TODO: habria que pasarle bien los colores q no se muy bien como se hace
            inactiveBarColor = Color.DarkGray,
            activeBarColor = Color(0xFF37B900),
            modifier = Modifier.size(200.dp),
            viewModel = viewModel
        )

       //TODO: tiene q renderear este composable de vuelta desp de que timer adelante el iterator


    }
}
