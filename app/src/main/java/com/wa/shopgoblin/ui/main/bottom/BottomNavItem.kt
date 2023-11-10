package com.wa.shopgoblin.ui.main.bottom

import com.wa.shopgoblin.R

interface BottomNavItem {
    val title: Int
    val icon: Int
    val route: String
}

object Home : BottomNavItem {
    override val title = R.string.home_nav_title
    override val icon = R.drawable.home_nav
    override val route: String
        get() = "home"
}

object Cart : BottomNavItem {
    override val title = R.string.cart_nav_title
    override val icon = R.drawable.cart_nav
    override val route: String
        get() = "cart"
}

object Orders : BottomNavItem {
    override val title = R.string.orders_nav_title
    override val icon = R.drawable.order_nav
    override val route: String
        get() = "orders"
}

object Wallet : BottomNavItem {
    override val title = R.string.wallet_nav_title
    override val icon = R.drawable.wallet_nav
    override val route: String
        get() = "wallet"
}

object Profile : BottomNavItem {
    override val title = R.string.profile_nav_title
    override val icon = R.drawable.peofile_nav
    override val route: String
        get() = "profile"
}

// Screens to be displayed in the bottom navigation
val bottomNavigationScreens = listOf(Home, Cart, Orders, Wallet, Profile)