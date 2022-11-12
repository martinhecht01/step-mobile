package com.example.step_mobile.components

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
import java.time.format.TextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineCard(title: String, description: String, isFavorite: Boolean) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
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
                FavoriteButton(isFavorite)
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = null,
                    modifier = Modifier.padding(15.dp).clickable {
                        context.startActivity(shareIntent)
                    }
                )
            }
            Text(
                text = description,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,)
        }
    }
}

@Composable
fun FavoriteButton(
    favInitialStatus: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63)
) {

    var isFavorite by remember { mutableStateOf(favInitialStatus) }

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