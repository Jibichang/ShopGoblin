package com.wa.shopgoblin.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.specialPlant
import com.wa.shopgoblin.ui.theme.Grey400
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme

//@Composable
//fun PlantListScreen() {
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        PlantHeader(header = stringResource(id = R.string.home_special_title))
//        PlantHorizontalList(plants = specialPlant)
//
//        PlantHeader(header = stringResource(id = R.string.home_popular_title))
//        PlantHorizontalGrid(plants = specialPlant)
//    }
//}

@Composable
fun PlantHeader(
    modifier: Modifier = Modifier,
    header: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            style = typography.titleLarge,
            modifier = modifier.weight(1f)
        )
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.home_special_all_title)),
            onClick = { },
            style = typography.headlineMedium
        )
    }
}

@Composable
fun PlantHorizontalList(
    plants: List<Plant>,
    listState: LazyListState = rememberLazyListState(),
    onFavoriteClick: (Plant) -> Unit = { },
    onItemClick: (Plant) -> Unit = { }
) {
    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        this.items(items = plants) { plant ->
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                PlantItem(
                    plant = plant,
                    onFavoriteClick = onFavoriteClick,
                    onItemClick = onItemClick
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun PlantHorizontalGrid(
    plants: List<Plant>,
    listState: LazyGridState = rememberLazyGridState()
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        state = listState,
    ) {
        this.items(plants) { plant ->
            PlantItem(plant = plant)
        }
    }
}

@Composable
fun PlantItem(
    modifier: Modifier = Modifier,
    plant: Plant,
    onFavoriteClick: (Plant) -> Unit = { },
    onItemClick: (Plant) -> Unit = { }
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .clickable { onItemClick(plant) }
    ) {
        PlantImageCard(modifier = modifier, plant = plant) {
            onFavoriteClick(plant)
        }

        Spacer(modifier = modifier.size(16.dp))
        Text(
            text = plant.name,
            style = typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.home_rating_icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = modifier.size(12.dp))
            Text(text = plant.rating)
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "$${plant.price}",
                style = typography.headlineLarge,
                modifier = modifier
            )
        }
    }
}

@Composable
fun PlantImageCard(
    modifier: Modifier = Modifier,
    plant: Plant,
    onFavoriteClick: (Plant) -> Unit = { }
) {
    Box {
        Image(
            modifier = modifier
                .size(200.dp)
                .clip(RoundedCornerShape(36.dp))
                .background(color = Grey400),
            painter = painterResource(id = plant.icon),
            contentDescription = plant.name
        )
        Box(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            IconButton(onClick = { onFavoriteClick(plant) }) {
                FavoriteIcon(modifier = modifier.size(18.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpecialDealScreenPreview() {
    ShopGoblinTheme {
        PlantHorizontalList(plants = specialPlant)
    }
}