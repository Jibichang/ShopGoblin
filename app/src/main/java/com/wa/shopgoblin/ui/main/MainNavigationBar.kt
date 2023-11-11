package com.wa.shopgoblin.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wa.shopgoblin.ui.main.cart.CartScreen
import com.wa.shopgoblin.ui.main.home.FavoriteScreen
import com.wa.shopgoblin.ui.main.home.HomeScreen
import com.wa.shopgoblin.ui.main.home.NotificationScreen
import com.wa.shopgoblin.ui.main.orders.OrdersScreen
import com.wa.shopgoblin.ui.main.profile.ProfileScreen
import com.wa.shopgoblin.ui.main.wallet.WalletScreen
import com.wa.shopgoblin.util.navigateSingleTopTo

@Composable
fun MainBottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onNotificationClick = {
                    navController.navigateSingleTopTo(Notification.route)
                }
            ) {
                navController.navigateSingleTopTo(Favorite.route)
            }
        }
        composable(route = Cart.route) {
            CartScreen(onClick = { navController.navigateSingleTopTo(Cart.route) })
        }
        composable(route = Orders.route) {
            OrdersScreen(onClick = { navController.navigateSingleTopTo(Orders.route) })
        }
        composable(route = Wallet.route) {
            WalletScreen()
        }
        composable(route = Profile.route) {
            ProfileScreen()
        }
        composable(route = Notification.route) {
            NotificationScreen()
        }
        composable(route = Favorite.route) {
            FavoriteScreen()
        }
    }
}

@Composable
fun MainBottomNavigationBar(
    allScreens: List<MainDestination>,
    onTabSelected: (MainDestination) -> Unit,
    currentScreen: MainDestination
) {

    NavigationBar {
        allScreens.forEach { screen ->
            MainNavigationBarItem(
                text = stringResource(id = screen.title),
                icon = screen.icon,
                onSelected = { onTabSelected(screen) },
                selected = currentScreen == screen
            )
        }
    }
}

@Composable
fun RowScope.MainNavigationBarItem(
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