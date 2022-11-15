package com.example.step_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.step_mobile.TopWorkoutCard
import com.example.step_mobile.WelcomeCard
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.util.getViewModelFactory
import com.example.step_mobile.R
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.PlayGreen
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, mainViewModel: MainViewModel){
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondonp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        if (mainViewModel.uiState.isFetching) {

        } else {
            var user = mainViewModel.uiState.currentUser
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var userName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var edit by remember { mutableStateOf(false) }
            if(user != null){
                firstName = user.firstName
                lastName = user.lastName
                userName = user.username
                email = user.email
            }
            Column(verticalArrangement = Arrangement.Top) {
                ScreenLogoutTitle(navController,  "Profile", mainViewModel)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(modifier = Modifier
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(500.dp)) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.height(100.dp) ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = {  },
                            modifier = Modifier.padding(vertical = 10.dp),
                            label = { Text("Email", fontSize = 15.sp) },
                            enabled = false,
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue, disabledBorderColor = Color.White, disabledLabelColor = Color.DarkGray, disabledTextColor = DarkBlue)
                        )

                        OutlinedTextField(
                            value = userName,
                            onValueChange = {  },
                            modifier = Modifier.padding(vertical = 10.dp),
                            label = { Text("Username", fontSize = 15.sp) },
                            enabled = false,
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue, disabledBorderColor = Color.White, disabledLabelColor = Color.DarkGray, disabledTextColor = DarkBlue)
                        )

                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            modifier = Modifier.padding(vertical = 10.dp),
                            label = { Text("First Name", fontSize = 15.sp) },
                            enabled = edit,
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue, disabledBorderColor = Color.White, disabledLabelColor = Color.DarkGray, disabledTextColor = DarkBlue)
                        )

                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            modifier = Modifier.padding(vertical = 10.dp),
                            label = { Text("Last Name", fontSize = 15.sp) },
                            enabled = edit,
                            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = DarkBlue, focusedLabelColor = DarkBlue, disabledBorderColor = Color.White, disabledLabelColor = Color.DarkGray, disabledTextColor = DarkBlue)
                        )

                        Button(
                            onClick = {
                                if(edit){
                                    //TODO: GUARDAR EL NUEVO PERFIL y getCurrentUser()
                                }
                                edit = !edit
                            },
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                                .size(60.dp),
                            shape = RoundedCornerShape(100),
                            elevation = ButtonDefaults.elevation(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = DarkBlue,
                                contentColor = Color.White
                            )
                        ) {
                            if(edit)
                                Icon(painterResource(id = R.drawable.ic_save), contentDescription = null)
                            else
                                Icon(painterResource(id = R.drawable.ic_edit), contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenLogoutTitle(navController: NavController, title: String, mainViewModel: MainViewModel){
    var scope = rememberCoroutineScope()
    Card(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .height(70.dp), elevation = 5.dp){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.padding(all = 10.dp))

        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
            Icon(painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                modifier = Modifier.clickable {
                    scope.launch {
                        mainViewModel.logout()
                        if(!mainViewModel.uiState.isAuthenticated){
                            navController.navigate("welcome_screen"){
                                popUpTo(0)
                            }
                        }
                    }
                }.padding(horizontal = 20.dp).size(35.dp),
            tint = Color.DarkGray)
        }
    }
}