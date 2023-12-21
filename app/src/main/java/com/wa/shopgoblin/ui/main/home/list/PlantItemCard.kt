package com.wa.shopgoblin.ui.main.home.list

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asAndroidColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.ui.main.home.user.FavoriteIcon
import com.wa.shopgoblin.ui.theme.Grey400
import com.wa.shopgoblin.util.loadImage

@Composable
fun PlantItem(
    modifier: Modifier = Modifier,
    plant: Plant,
    onFavoriteClick: (Plant, Boolean) -> Unit = { _,_ -> },
    onItemClick: (Plant) -> Unit = { }
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .clickable { onItemClick(plant) }
    ) {
        PlantImageCardWithFavorite(plant = plant) { item, checked ->
            onFavoriteClick(item, checked)
        }

        Spacer(modifier = modifier.size(16.dp))
        Text(
            text = plant.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingScoreMinimize(rating = plant.rating)

            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "$${plant.price}",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun PlantImageCardWithFavorite(
    modifier: Modifier = Modifier,
    plant: Plant,
    onFavoriteClick: (Plant, Boolean) -> Unit = { _, _ -> }
) {
    Box {
//        Image(
//            modifier = modifier
//                .size(200.dp)
//                .clip(RoundedCornerShape(36.dp))
//                .background(color = Grey400),
//            painter = painterResource(id = plant.icon),
//            contentDescription = plant.name
//        )

        PlantImageCard (
            modifier = modifier.size(200.dp),
            icon = plant.icon
        )
        Box(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            IconButton( onClick = {
                val checked = !plant.favorite
                onFavoriteClick(plant, checked)
            }) {
                FavoriteIcon(
                    modifier = modifier.size(18.dp),
                    checked = plant.favorite
                )
            }
        }
    }
}

@Composable
fun PlantImageCard(
    modifier: Modifier = Modifier,
    icon: String
) {
    AppImage(
        modifier = modifier
            .clip(RoundedCornerShape(36.dp))
            .background(color = Grey400),
        resource = loadImage(LocalContext.current, icon)
    )
}

@Composable
fun RatingScoreMinimize(
    modifier: Modifier = Modifier,
    rating: String
) {
    Icon(
        painter = painterResource(id = R.drawable.home_rating_icon),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = modifier.size(12.dp))
    Text(text = rating)
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes resource: Int,
    colorFilter: ColorFilter? = null
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                setImageResource(resource)
                setColorFilter(colorFilter?.asAndroidColorFilter())
            }
        },
        update = {
            it.setImageResource(resource)
            it.colorFilter = colorFilter?.asAndroidColorFilter()
        }
    )
}