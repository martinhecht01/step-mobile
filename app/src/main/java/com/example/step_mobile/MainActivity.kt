package com.example.step_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.step_mobile.ui.theme.BottomNavigationTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.ui.theme.StepmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    MyNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun loginButton(){
    Button(onClick = { /*TODO*/ }, modifier = Modifier.width(140.dp).height(60.dp) ,colors = ButtonDefaults.buttonColors( contentColor = Color.White), shape = RoundedCornerShape(40.dp), elevation =  ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 8.dp,)
    ) {
        Text("Login", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.SearchScreen,
        Screen.PlayScreen,
        Screen.MyWorkoutsScreen,
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun RoutineCard() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(shape = RoundedCornerShape(20.dp),elevation = 10.dp, modifier = paddingModifier) {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Routine Title", modifier = paddingModifier)
            Text(text = "Routine Description", modifier = paddingModifier)
            FavoriteButton()




        }


    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63)
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}