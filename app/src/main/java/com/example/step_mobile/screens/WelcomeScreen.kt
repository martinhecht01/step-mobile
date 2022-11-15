package com.example.step_mobile.screens

import android.graphics.Paint.Align
import androidx.annotation.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import kotlinx.coroutines.launch

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreen(navController: NavController, mainViewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .paddingFromBaseline(bottom = 50.dp)
                .padding(50.dp)
        ) {
            titleText(stringResource(R.string.welcome))
            subtitleText(stringResource(R.string.welcome_text))
            loginButton(navController, "login_screen")
        }
    }
}

@Composable
fun loginButton(navController: NavController, route: String) {
    Button(
        onClick = {
            navController.navigate(route)},
        modifier = Modifier
            .padding(bottom = 20.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color(0xFF55B8FF)
        ),

        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 8.dp)
    ) {
        Text(stringResource(R.string.login), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
    }
}

@Composable
fun subtitleText(text: String) {
    Text(
        text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
       // textAlign = TextAlign.Justify,
        modifier = Modifier.padding(bottom = 50.dp)
    )
}

@Composable
fun titleText(text: String) {
    Text(
        text,
        fontSize = (55/ LocalDensity.current.fontScale).sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}
