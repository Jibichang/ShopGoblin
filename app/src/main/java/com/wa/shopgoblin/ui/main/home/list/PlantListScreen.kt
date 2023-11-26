package com.wa.shopgoblin.ui.main.home.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant

@Composable
fun PlantListScreen(
    specialPlants: List<Plant>,
    plants: List<Plant>,
    listState: LazyGridState = rememberLazyGridState(),
    onFavoriteClick: (Plant, Boolean) -> Unit = { _,_ -> },
    onItemClick: (Plant) -> Unit = { }
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        state = listState
    ) {
        val gridItemSpan = GridItemSpan(2)
        item(span = { gridItemSpan }) {
            PlantHeader(header = stringResource(id = R.string.home_special_title))
        }
        item(span = { gridItemSpan }) {
            PlantHorizontalList(
                plants = specialPlants,
                onFavoriteClick = { item, checked -> onFavoriteClick(item, checked) },
                onItemClick = {
                    onItemClick(it)
                }
            )
        }
        item(span = { gridItemSpan }) {
            PlantHeader(header = stringResource(id = R.string.home_popular_title))
        }
        this.items(plants) { plant ->
            PlantItem(
                plant = plant,
                onFavoriteClick = { item, checked -> onFavoriteClick(item, checked) },
                onItemClick = {
                    onItemClick(it)
                }
            )
        }
    }
}

@Composable
fun PlantHeader(
    modifier: Modifier = Modifier,
    header: String,
    onClickSeeAll: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            style = typography.titleLarge,
            modifier = modifier.weight(1f)
        )
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.home_special_all_title)),
            onClick = { onClickSeeAll() },
            style = typography.headlineMedium
        )
    }
}

@Composable
fun PlantHorizontalList(
    plants: List<Plant>,
    listState: LazyListState = rememberLazyListState(),
    onFavoriteClick: (Plant, Boolean) -> Unit = { _,_ -> },
    onItemClick: (Plant) -> Unit = { }
) {
    LazyRow(state = listState) {
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