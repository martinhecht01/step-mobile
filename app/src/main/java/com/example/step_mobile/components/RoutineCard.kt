package com.example.step_mobile.components

import RatingBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.step_mobile.MainActivity
import com.example.step_mobile.classes.MainViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineCard(title: String, description: String, score: Int , isFavorite: Boolean, id: Int, mainViewModel: MainViewModel) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "view_routine_screen")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    val paddingModifier = Modifier.padding(10.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = paddingModifier
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(Modifier.align(Alignment.End)) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = paddingModifier.weight(1f)
                )
                FavoriteButton(isFavorite, mainViewModel = mainViewModel, id = id)
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
                text = description,
                modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 10.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,)
            RatingBar(rating = score.toDouble(), modifier = Modifier.padding(horizontal = 10.dp))
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