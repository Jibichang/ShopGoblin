@file:OptIn(ExperimentalMaterial3Api::class)

package com.wa.shopgoblin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wa.shopgoblin.domain.UserStateHolder
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme
import com.wa.shopgoblin.util.navigateSingleTopTo
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopGoblinTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun ViewModelDatabase(viewModel: UserViewModel = koinViewModel()) {
    val helloText by viewModel.username.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserData()
    }

    Text(text = helloText, modifier = Modifier.padding(8.dp))
}

@Composable
fun FactoryInject(userName: String, presenter: UserStateHolder = koinInject()) {
    Text(text = presenter.sayHello(userName), modifier = Modifier.padding(8.dp))
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    ShopGoblinTheme {
        MainApp()
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val currentScreen = allScreens.find {
        it.route == currentDestination?.route } ?: Home
    val isHomeScreen = bottomNavigationScreens.find {
        it.route == currentDestination?.route } != null

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (!isHomeScreen) {
                TopBarTransparent(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        },
        bottomBar = {
            if (isHomeScreen) {
                MainBottomNavigationBar(
                    allScreens = bottomNavigationScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )
            }
        }
    ) { paddingValues ->
        MainBottomNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun TopBarTransparent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    currentScreen: MainDestination = Home
) {
    TopAppBar(
        title = {
            if (currentScreen != Home) {
                Text(text = stringResource(id = currentScreen.title))
            }
        },
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
