package com.example.step_mobile.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import android.renderscript.ScriptGroup.Input
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
import com.example.step_mobile.data.model.SignUp
import com.example.step_mobile.mainContent
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController, viewModel : MainViewModel) {
    Surface(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        var email by remember{mutableStateOf("")}
        var firstName by remember{mutableStateOf("")}
        var lastName by remember{mutableStateOf("")}
        var username by remember{mutableStateOf("")}
        var password by remember{mutableStateOf("")}
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box(modifier = Modifier.clip(shape = RoundedCornerShape(30.dp)), contentAlignment = Alignment.Center){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
                Box(modifier = Modifier
                    .background(color = White)
                    .padding(30.dp)){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Box(modifier = Modifier.padding(bottom = 15.dp), contentAlignment = Alignment.Center){
                            Text(
                                stringResource(id = R.string.signup),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.DarkGray)
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            Text(
                                stringResource(id = R.string.already_user),
                                fontSize = 20.sp,
                                color = PlayGreen,
                                style = TextStyle(textDecoration = TextDecoration.Underline),
                                modifier = Modifier.clickable {
                                navController.navigate("login_screen?id=${-1}")
                            })
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            firstName = InputField(stringResource(id = R.string.first_name))
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            lastName = InputField(stringResource(id = R.string.last_name))
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center) {
                            email = InputField(label = "Email")
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            username = InputField(label = stringResource(id = R.string.username))
                        }
                        Box(modifier = Modifier.padding(bottom = 25.dp), contentAlignment = Alignment.Center){
                            password = PasswordTextField()
                        }
                        Box(modifier=Modifier.padding(bottom = 10.dp),contentAlignment = Alignment.Center){
                            signUpButton(navController, viewModel, username, password, email, firstName, lastName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun signUpButton(navController: NavController,viewModel : MainViewModel, username : String, password : String, email: String, firstName: String, lastName: String){
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch{
                viewModel.signUp(SignUp(username = username, email = email, firstName = firstName, password = password, lastName = lastName)).invokeOnCompletion {
                    if(viewModel.uiState.message == null)
                        navController.navigate("verify_screen")
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
        Text(
            stringResource(id = R.string.signup),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify)
    }
}
