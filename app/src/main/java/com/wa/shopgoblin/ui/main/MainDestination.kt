package com.wa.shopgoblin.ui.main

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.wa.shopgoblin.R

interface MainDestination {
    val title: Int
    val icon: Int
    val route: String
}

object Home : MainDestination {
    override val title = R.string.home_nav_title
    override val icon = R.drawable.home_nav
    override val route: String
        get() = "home"
}

object Cart : MainDestination {
    override val title = R.string.cart_nav_title
    override val icon = R.drawable.cart_nav
    override val route: String
        get() = "cart"
}

object Orders : MainDestination {
    override val title = R.string.orders_nav_title
    override val icon = R.drawable.order_nav
    override val route: String
        get() = "orders"
}

object Wallet : MainDestination {
    override val title = R.string.wallet_nav_title
    override val icon = R.drawable.wallet_nav
    override val route: String
        get() = "wallet"
}

object Profile : MainDestination {
    override val title = R.string.profile_nav_title
    override val icon = R.drawable.peofile_nav
    override val route: String
        get() = "profile"
}

object Notification : MainDestination {
    override val title = R.string.notification_title
    override val icon = R.drawable.home_bell_icon
    override val route: String
        get() = "notification"
}

object Favorite : MainDestination {
    override val title = R.string.favorite_title
    override val icon = R.drawable.home_favorite_icon
    override val route: String
        get() = "favorite"
}

object Detail : MainDestination {
    override val title = R.string.home_nav_title
    override val icon = R.drawable.home_nav
    override val route: String
        get() = "detail"

    const val plantArg = "plant_arg"
    val routeWithArgs = "${route}/{${plantArg}}"
    val arguments = listOf(
        navArgument(plantArg) { type = NavType.IntType }
    )
}

// Screens to be displayed in the bottom navigation
val bottomNavigationScreens = listOf(Home, Cart, Orders, Wallet, Profile)

// Screens to be displayed in the bottom navigation
val allScreens = listOf(Home, Cart, Orders, Wallet, Profile, Notification, Favorite, Detail)
