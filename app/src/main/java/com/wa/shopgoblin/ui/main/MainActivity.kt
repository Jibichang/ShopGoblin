@file:OptIn(ExperimentalMaterial3Api::class)

package com.wa.shopgoblin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.wa.shopgoblin.R
import com.wa.shopgoblin.domain.UserStateHolder
import com.wa.shopgoblin.ui.main.bottom.BottomNavItem
import com.wa.shopgoblin.ui.main.bottom.Home
import com.wa.shopgoblin.ui.main.bottom.bottomNavigationScreens
import com.wa.shopgoblin.ui.main.home.HomeScreen
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopGoblinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewModelDatabase()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopGoblinTheme {
        FactoryInject("Android")
    }
}

@Composable
fun ViewModelInject(userName: String, viewModel: UserViewModel = koinViewModel()) {
    Text(text = viewModel.sayHello(userName), modifier = Modifier.padding(8.dp))
}

@Composable
fun ViewModelDatabase(viewModel: UserViewModel = koinViewModel()) {
    val helloText by viewModel.helloText.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.addData()
    }

    Text(text = helloText, modifier = Modifier.padding(8.dp))
}

@Composable
fun FactoryInject(userName: String, presenter: UserStateHolder = koinInject()) {
    Text(text = presenter.sayHello(userName), modifier = Modifier.padding(8.dp))
}

@Composable
fun RowScope.HomeBottomNavItem(
    text: String,
    icon: Int,
    onSelected: () -> Unit,
    selected: Boolean
) {
    NavigationBarItem(
        label = { Text(text = text) },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text
            )
        },
        selected = selected,
        alwaysShowLabel = true,
        onClick = onSelected,
        colors = NavigationBarItemDefaults.colors()
    )
}

@Composable
fun HomeBottomNavigation() {

    NavigationBar {
        bottomNavigationScreens.forEach { item ->
            HomeBottomNavItem(
                text = stringResource(id = item.title),
                icon = item.icon,
                onSelected = { },
                selected = true
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    ShopGoblinTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                HomeBottomNavigation()
            }
        ) { paddingValues ->
//            BottomNavHost(navController = navController,
//                modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = stringResource(id = Home.title),
        modifier = modifier
    ) {

    }
}
