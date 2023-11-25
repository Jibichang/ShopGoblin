@file:OptIn(ExperimentalMaterial3Api::class)

package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.ui.main.home.PlantViewModel
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme
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

    LaunchedEffect(Unit) {
        plantViewModel.getPlant(plantId)
    }

    Scaffold(
        topBar = {
            TopBarTransparent(navController = navController)
        }
    ) { paddingValues ->
        plantDetail?.let { plant ->
            PlantDetailContent(plant = plant, paddingValues = paddingValues,
                onFavoriteClick = { item, checked ->
                    scope.launch {
                        plantViewModel.saveFavorite(plant = item, checked = checked)
                    }
                },
                onClickCart = {
                    println("----------AddToCartButton quantityCount $it")
                })
        }
    }
}

@Composable
fun TopBarTransparent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(
                modifier = modifier.padding(start = 8.dp),
                onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun PlantDetailContent(
    plant: Plant,
    paddingValues: PaddingValues,
    onFavoriteClick: (Plant, Boolean) -> Unit = { _, _ -> },
    onClickCart: (Int) -> Unit = {}
) {
    val quantityCount: MutableIntState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(360.dp),
                painter = painterResource(loadImage(LocalContext.current, plant.icon)), contentDescription = plant.name,
                contentScale = ContentScale.FillHeight
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
                TitleAndRatingSection(plant = plant, onFavoriteClick = onFavoriteClick)
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
            AdjustQuantityButton(quantity = quantityCount)
        }

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 12.dp),
            color = MaterialTheme.colorScheme.secondary
        )

        AddToCartTab(plant) {
            AddToCartButton {
                onClickCart(quantityCount.intValue)
            }
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
//        PlantDetailScreen(plant)
    }
}