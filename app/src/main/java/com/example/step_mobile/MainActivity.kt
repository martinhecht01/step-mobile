package com.example.step_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.step_mobile.ui.theme.StepmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(painter = painterResource(id = R.drawable.fondo), contentDescription = null)
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(25.dp)
                    ){
                        loginButton()
                        signupButton()
                    }
                    //Greeting("Pedro")
                }
            }
        }
    }
}

@Composable
fun backGroundImg(){
    val image = painterResource(R.drawable.fondo);
    Box(Modifier.fillMaxSize()) {
        Image(painter = image, contentDescription = null)
    }
}

@Composable
fun loginButton(){
    Button(onClick = { /*TODO*/ }) {
       Text("Login")
    }
}

@Composable
fun signupButton(){
    Button(onClick = { /*TODO*/ }) {
        Text("Sign Up")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", Modifier.firstBaselineToTop(100.dp))
}

//Padding TOP
fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StepmobileTheme {
        Greeting("Android")
    }
}