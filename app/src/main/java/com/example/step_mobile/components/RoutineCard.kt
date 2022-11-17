package com.example.step_mobile.components

import StarBar
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.example.step_mobile.MainActivity
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.data.model.Routine
import java.time.format.TextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineCard(routine: Routine, mainViewModel: MainViewModel) {
    var uri = "https://www.stepapp.me"


    val context = LocalContext.current
    val deepLinkIntent = Intent(
        Intent.ACTION_SEND,
        "https://www.stepapp.me/${routine.id}".toUri(),
        context,
        Routine::class.java
    )

    val deepLinkIntent2 : Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,"https://www.stepapp.me/${routine.id}")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(deepLinkIntent2, "Share to")

    val paddingModifier = Modifier.padding(10.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = paddingModifier
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(Modifier.align(Alignment.End)) {
                Text(
                    text = routine.name.toUpperCase(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = paddingModifier.weight(1f)
                )
                FavoriteButton(mainViewModel.isFavourite(routine.id), mainViewModel = mainViewModel, id = routine.id)
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            context.startActivity(shareIntent)
                        }
                )
            }
            Text(
                text = (routine.difficulty ?: "").toUpperCase(),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp, top = 0.dp),
                maxLines = 1,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis)
            Text(
                text = routine.detail,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
            StarBar(rating = (routine.score ?: 0).toDouble()/2, modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}

@Composable
fun FavoriteButton(
    favInitialStatus: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    mainViewModel: MainViewModel,
    id: Int
) {

    var isFavorite by remember { mutableStateOf(favInitialStatus) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            if(isFavorite){
                mainViewModel.deleteFromFavourites(id)
            }else{
                mainViewModel.addToFavourites(id)
            }
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