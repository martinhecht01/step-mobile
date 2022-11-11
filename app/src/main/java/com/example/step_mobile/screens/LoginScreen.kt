package com.example.step_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.ui.theme.DarkBlue


@Composable
//@Preview
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box(modifier = Modifier.clip(shape = RoundedCornerShape(30.dp)), contentAlignment = Alignment.Center){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
                Box(modifier = Modifier
                    .background(color = White)
                    .padding(30.dp)){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            Text(stringResource(R.string.signin), fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.DarkGray)
                        }
//                        Box(modifier = Modifier.padding(bottom = 25.dp)){
//                            Text(
//                                buildAnnotatedString {
//                                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 20.sp)) {
//                                        append("New to Step? ")
//                                    }
//                                    withStyle(style = SpanStyle(color = Color.Green, textDecoration = TextDecoration.Underline, fontSize = 20.sp)) {
//                                        append("Sign up for free")
//                                    }
//                                }
//                            )
//                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            InputField(label = "Email")
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            PasswordTextField()
                        }
                        Box(modifier=Modifier.padding(bottom = 10.dp),contentAlignment = Alignment.Center){
                            loginContinueButton(navController, "home_screen", )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputField(label : String) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        modifier = Modifier.background(Color.Transparent),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun loginContinueButton(navController: NavController, route: String){
    Button(
        onClick = {
            navController.navigate(route){
                popUpTo("welcome_screen") { inclusive = true }
            } },
        modifier = Modifier
            .width(250.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = White,
            backgroundColor = DarkBlue
        ),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 8.dp)
    ) {
        Text(stringResource(R.string.login), fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
    }
}


@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(stringResource(R.string.password)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

