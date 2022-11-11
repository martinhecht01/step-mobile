
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.step_mobile.R
import com.example.step_mobile.ui.theme.DarkBlue
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = DarkBlue,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    val icons = listOf(
        painterResource(id = R.drawable.ic_outline_star),
        painterResource(id = R.drawable.ic_star),
        painterResource(id = R.drawable.ic_star_half)
    )

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(icons[1], contentDescription = null, tint = starsColor)
        }

        if(halfStar){
            Icon(icons[2], contentDescription = null, tint = starsColor)
        }

        repeat(unfilledStars) {
            Icon(
                icons[0],
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}
