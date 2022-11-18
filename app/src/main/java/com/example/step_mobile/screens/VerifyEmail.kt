package com.example.step_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.data.model.SignUp
import com.example.step_mobile.data.model.Verify
import com.example.step_mobile.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@Composable
fun VerifyScreen(navController: NavController, viewModel : MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        var email by remember{ mutableStateOf("") }
        var code by remember{ mutableStateOf("") }
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box(modifier = Modifier.clip(shape = RoundedCornerShape(30.dp)), contentAlignment = Alignment.Center){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
                Box(modifier = Modifier
                    .background(color = Color.White)
                    .padding(30.dp)){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            Text("Verify Email", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.DarkGray)
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            email = InputField(label = "Email", false)
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            code = InputField(label = "Code", false)
                        }
                        Box(modifier= Modifier.padding(bottom = 10.dp),contentAlignment = Alignment.Center){
                            VerifyButton(navController, viewModel, email, code)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VerifyButton(navController: NavController, viewModel : MainViewModel, email: String, code: String){
    val scope = rememberCoroutineScope()
    var errorFlag by remember { mutableStateOf(false) }
    if(errorFlag){
        Text(text = "Error: Invalid code or email", fontSize = 10.sp, color = Color.Red)
    }
    Button(
        onClick = {
            scope.launch{
                viewModel.verify(Verify(email, code)).invokeOnCompletion { 
                    if(viewModel.uiState.message != null){
                        navController.navigate("login_screen?id=${-1}")
                    } else
                        errorFlag = true
                }
            } },
        modifier = Modifier
            .width(250.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = DarkBlue
        ),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 8.dp)
    ) {
        Text("Verify", fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
    }
}