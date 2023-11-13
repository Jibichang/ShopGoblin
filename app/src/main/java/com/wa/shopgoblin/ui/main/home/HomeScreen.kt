package com.wa.shopgoblin.ui.main.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.specialPlant
import com.wa.shopgoblin.ui.main.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: UserViewModel = koinViewModel(),
    onNotificationClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val userName by viewModel.username.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserData()
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
            SearchBarScreen()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .scrollable(rememberScrollableState { it }, Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                item {
                    PlantHeader(header = stringResource(id = R.string.home_special_title))
                }
                item {
                    PlantHorizontalList(
                        plants = specialPlant,
                        onFavoriteClick = { plant ->
                            println("---------------plant special onFavoriteClick - ${plant.name}")
                        },
                        onItemClick = { plant ->
                            println("---------------plant special - ${plant.name}")
                        }
                    )
                }
                item {
                    PlantHeader(header = stringResource(id = R.string.home_popular_title))
                }
                item {
                    PlantHorizontalList(
                        plants = specialPlant.reversed(),
                        onFavoriteClick = { plant ->
                            println("---------------plant popular onFavoriteClick - ${plant.name}")
                        }, onItemClick = { plant ->
                            println("---------------plant popular - ${plant.name}")
                        }
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNavigationPreview() {
    HomeScreen()
}