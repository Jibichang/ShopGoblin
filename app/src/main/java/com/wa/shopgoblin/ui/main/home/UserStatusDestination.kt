package com.wa.shopgoblin.ui.main.home

import com.wa.shopgoblin.R

interface UserStatusDestination {
    val icon: Int
    val route: String
}

object Notification : UserStatusDestination {
    override val icon = R.drawable.home_bell_icon
    override val route: String
        get() = "notification"
}

object Favorite : UserStatusDestination {
    override val icon = R.drawable.home_favorite_icon
    override val route: String
        get() = "favorite"
}
