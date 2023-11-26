@file:OptIn(ExperimentalMaterial3Api::class)

package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.plant1
import com.wa.shopgoblin.ui.main.home.PlantViewModel
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme
import com.wa.shopgoblin.util.isScrollingTop
import com.wa.shopgoblin.util.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantDetailScreen(
    plantId: Int?,
    navController: NavController = rememberNavController(),
    plantViewModel: PlantViewModel = koinViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val plantDetail by plantViewModel.plantDetail.collectAsState()
    val onFavoriteClick: (Plant, Boolean) -> Unit = { item, checked ->
        scope.launch {
            plantViewModel.saveFavorite(plant = item, checked = checked) {
                plantViewModel.getPlant(plantId)
            }
        }
    }

    LaunchedEffect(Unit) {
        plantViewModel.getPlant(plantId)
    }

    plantDetail?.let { plant ->
        PlantDetailContent(
            image = { PlantDetailImage(icon = plant.icon, name = plant.name) },
            header = {
                TitleAndRatingSection(
                    plant = plant,
                    onFavoriteClick = onFavoriteClick
                )
            },
            description = { DescriptionSection(plant.description ?: "") }
        ) { quantity ->
            AddToCartTab(plant.price) {
                AddToCartButton(enabled = quantity != 0) {
                    println("----------AddToCartButton quantityCount $quantity")
                }
            }
        }
    }
}

@Composable
fun PlantDetailContent(
    listState: LazyListState = rememberLazyListState(),
    image: @Composable () -> Unit = {},
    header: @Composable () -> Unit = {},
    description: @Composable () -> Unit = {},
    cart: @Composable (Int) -> Unit = {}
) {
    val quantityCount: MutableIntState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        image()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item { header() }
            item {
                DetailDivider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
            }
            item { description() }
            item {
                AdjustQuantityTab {
                    AdjustQuantityButton(quantity = quantityCount)
                }
            }
        }
        AnimatedVisibility(
            visible = !(listState.isScrollingTop().value)
        ) {
            DetailDivider()
        }

        cart(quantityCount.intValue)
    }
}

@Composable
fun DetailDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun PlantDetailImage(
    modifier: Modifier = Modifier,
    icon: String,
    name: String
) {
    Row(
        modifier = modifier.fillMaxHeight(0.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(loadImage(LocalContext.current, icon)),
            contentDescription = name,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ShopGoblinTheme {
        plant1.let { plant ->
            PlantDetailContent(
                image = {
                    PlantDetailImage(icon = plant.icon, name = plant.name)
                },
                header = {
                    TitleAndRatingSection(plant = plant, onFavoriteClick = { _, _ -> })
                },
                description = {
                    DescriptionSection(plant.description ?: "")
                }
            ) { quantity ->
                AddToCartTab(plant.price) {
                    AddToCartButton(enabled = quantity != 0) {
                        println("----------AddToCartButton quantityCount $quantity")
                    }
                }
            }
        }
    }
}