package com.wa.shopgoblin.data.database.plant

import com.wa.shopgoblin.R

data class Plant(
    val id: Int,
    val name: String,
    val icon: Int,
    val rating: String,
    val price: Int
)

val specialPlant = listOf(
    Plant(
        id = 1,
        name = "Sansevieria Trifasciata Snake Plant",
        icon = R.drawable.plant_1,
        rating = "4.5",
        price = 32
    ),
    Plant(
        id = 2,
        name = "Prayer Plant",
        icon = R.drawable.plant_2,
        rating = "4.8",
        price = 29
    ),
    Plant(
        id = 3,
        name = "Sago Palm Plant",
        icon = R.drawable.plant_3,
        rating = "4.7",
        price = 28
    ),
    Plant(
        id = 4,
        name = "Fiddle Leaf Fig",
        icon = R.drawable.plant_4,
        rating = "4.3",
        price = 36
    ),
    Plant(
        id = 5,
        name = "Chinese Money",
        icon = R.drawable.plant_5,
        rating = "4.9",
        price = 34
    ),
    Plant(
        id = 6,
        name = "Ear Cactus Plant",
        icon = R.drawable.plant_6,
        rating = "4.6",
        price = 27
    )
)