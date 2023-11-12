package com.wa.shopgoblin.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            UserStatusBar(
                userName = userName,
                onNotificationClick = onNotificationClick,
                onFavoriteClick = onFavoriteClick
            )
            SearchBarScreen()
            PlantHomeScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNavigationPreview() {
    HomeScreen()
}