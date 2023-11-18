package com.wa.shopgoblin.ui.main.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wa.shopgoblin.ui.main.UserViewModel
import com.wa.shopgoblin.util.isScrollingTop
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: UserViewModel = koinViewModel(),
    plantViewModel: PlantViewModel = koinViewModel(),
    onNotificationClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val userName by viewModel.username.collectAsState()
    val plants by plantViewModel.plantList.collectAsState()
    val listState: LazyGridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.getUserData()
        plantViewModel.getPlantList()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            UserStatusBar(
                userName = userName,
                onNotificationClick = onNotificationClick,
                onFavoriteClick = onFavoriteClick
            )

            AnimatedVisibility(
                visible = listState.isScrollingTop().value
            ) {
                SearchBarScreen()
            }

            PlantListScreen(plants, listState = listState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNavigationPreview() {
    HomeScreen()
}