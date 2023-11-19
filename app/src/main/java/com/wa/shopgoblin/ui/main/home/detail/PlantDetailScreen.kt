package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.plant
import com.wa.shopgoblin.ui.main.home.FavoriteIcon
import com.wa.shopgoblin.ui.main.home.RatingScoreMinimize
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme

@Composable
fun PlantDetailScreen(
    plant: Plant
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 24.dp, end = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxHeight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(360.dp),
                painter = painterResource(plant.icon), contentDescription = plant.name,
                contentScale = ContentScale.FillHeight
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
                TitleAndRatingSection(plant)
            }
            item {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            item {
                DescriptionSection(plant.description ?: "")
            }
        }

        AdjustQuantityTab(plant) {
            AdjustQuantityButton()
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            color = MaterialTheme.colorScheme.secondary
        )

        AddToCartTab(plant) {
            AddToCartButton()
        }
    }

}

@Composable
fun AddToCartButton(
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 8.dp,
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home_cart_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = stringResource(id = R.string.home_detail_cart_title))
        }
    }
}

@Composable
fun AddToCartTab(
    plant: Plant,
    button: @Composable (Plant) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.home_detail_total_title),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "$${plant.price}",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        button(plant)
    }
}

@Composable
fun AdjustQuantityButton(
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.wrapContentWidth(),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.home_quantity_minus_icon),
                    contentDescription = "reduce quantity"
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "0", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.size(20.dp))
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.home_quantity_plus_icon),
                    contentDescription = "add quantity"
                )
            }
        }
    }
}

@Composable
fun AdjustQuantityTab(
    plant: Plant,
    button: @Composable (Plant) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.home_detail_quantity_title),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(20.dp))
        button(plant)
    }
}

@Composable
fun DescriptionSection(
    description: String
) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(id = R.string.home_detail_description_title),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TitleAndRatingSection(
    plant: Plant
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = plant.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(12.dp))
            FavoriteIcon(
                modifier = Modifier.size(28.dp),
                checked = plant.favorite
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingScoreMinimize(rating = plant.rating, modifier = Modifier.size(4.dp))
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "(${plant.reviewer} reviews)",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ShopGoblinTheme {
//        AdjustQuantityButton()
//        AddToCartTab(plant) {
//            AddToCartButton()
//        }
//        AdjustQuantityTab(plant) {
//            AdjustQuantityButton()
//        }
//        DescriptionSection(plant)
        PlantDetailScreen(plant)
    }
}