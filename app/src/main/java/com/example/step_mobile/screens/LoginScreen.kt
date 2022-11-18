package com.example.step_mobile.screens

import android.text.style.UnderlineSpan
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.mainContent
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//viewModel : MainViewModel = factory = getViewModelFactory()

@Composable
//@Preview
fun LoginScreen(navController: NavController, viewModel : MainViewModel, id: Int ) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        var username by remember{mutableStateOf("")}
        var password by remember{mutableStateOf("")}
        var error by remember{mutableStateOf("")}
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box(modifier = Modifier.clip(shape = RoundedCornerShape(30.dp)), contentAlignment = Alignment.Center){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
                Box(modifier = Modifier
                    .background(color = White)
                    .padding(30.dp)){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Box(modifier = Modifier.padding(bottom = 5.dp), contentAlignment = Alignment.Center){
                            Text(stringResource(R.string.signin), fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.DarkGray)
                        }
                        Box(modifier = Modifier.padding(bottom = 5.dp), contentAlignment = Alignment.Center){
                            Text(
                                stringResource(id = R.string.signup_for_free),
                                fontSize = 20.sp,
                                color = PlayGreen,
                                style = TextStyle(textDecoration = TextDecoration.Underline),
                                modifier = Modifier.clickable {
                                navController.navigate("sign_up_screen")
                            })
                        }
                        Box(modifier = Modifier.padding(bottom = 10.dp), contentAlignment = Alignment.Center){
                            Text(
                                error,
                                fontSize = 20.sp,
                                color = Color.Red,
                            )
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                             username = InputField(label = stringResource(id = R.string.username), error != "")
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                             password = PasswordTextField(error != "")
                        }
                        Box(modifier=Modifier.padding(bottom = 10.dp),contentAlignment = Alignment.Center){
                            error = loginContinueButton(navController, viewModel,"home_screen", username, password, id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputField(label : String, error: Boolean) : String{
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        isError = error,
        modifier = Modifier.background(Color.Transparent),
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
    return text
}

@Composable
fun loginContinueButton(navController: NavController,viewModel : MainViewModel, route: String, username : String, password : String, id: Int) : String{
    val scope = rememberCoroutineScope()
    var error by remember{mutableStateOf("")}

    Button(
        onClick = {
            scope.launch{
                viewModel.login(username,password).invokeOnCompletion {
                    if(viewModel.uiState.isAuthenticated) {
                        viewModel.getCurrentUser().invokeOnCompletion {
                            viewModel.getRoutines().invokeOnCompletion {
                                if(id == -1) {
                                    navController.navigate(route) {
                                        popUpTo("welcome_screen") { inclusive = true }
                                    }
                                } else{
                                    navController.navigate("share_screen?id=${id}"){
                                        popUpTo("welcome_screen")
                                    }
                                }
                            }
                        }
                    } else{
                        error = viewModel.uiState.message!!
                    }
                }
            } },
        modifier = Modifier
            .width(250.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = White,
            backgroundColor = DarkBlue
        ),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 8.dp)
    ) {
        Text(stringResource(R.string.login), fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
    }
    if(error != ""){
        return stringResource(id = R.string.invalid_fields)
    }
    return error
}


@Composable
fun PasswordTextField(error: Boolean) : String{
    var password by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(stringResource(R.string.password)) },
        isError = error,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue)
    )
    return password
}

