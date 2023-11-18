package com.wa.shopgoblin.ui.main.home

import androidx.lifecycle.ViewModel
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.PlantDao
import com.wa.shopgoblin.data.database.plant.randFloat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantViewModel(private val plantDao: PlantDao) : ViewModel() {

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList: StateFlow<List<Plant>> = _plantList

    fun getPlantList() {
        CoroutineScope(Dispatchers.IO).launch {

            val plants = plantDao.getAll()
            _plantList.value = plants
        }
    }

    private fun insertPlants() {
        val plant = Plant(
            name = "Cereus repandus",
            icon = R.drawable.plant_11,
            description = "Cereus repandus, also known as the Peruvian Apple Cactus, boasts a tall, columnar stem adorned with numerous ribs and spines, culminating in large, showy white flowers at night. This striking cactus, native to South America, bears edible red or yellowish fruits, known as Peruvian apples, making it not only an ornamental marvel but also a potential food source in some regions, while its impressive nocturnal blossoms add to its allure in gardens and landscapes.",
            rating = String.format("%.2f", randFloat(3f, 5f)),
            reviewer = (1000..5000).random(),
            price = String.format("%.0f", randFloat(20f, 50f)).toDouble(),
            spacial = (0..1).random() == 1,
            quantity = (50..150).random()
        )
        val plant2 = Plant(
            name = "Cereus jamacaru",
            icon = R.drawable.plant_12,
            description = "Cereus jamacaru, commonly referred to as the Mandacaru cactus, features a tall, branching stem covered in prominent ribs and sharp spines. Native to South America, this cactus species showcases nocturnal white flowers and eventually produces edible red or purplish fruits. Its imposing stature and adaptability to arid environments make it a distinctive presence in landscapes and natural habitats across various regions.",
            rating = String.format("%.2f", randFloat(3f, 5f)),
            reviewer = (1000..5000).random(),
            price = String.format("%.0f", randFloat(20f, 50f)).toDouble(),
            spacial = (1..19).random() %2 == 0,
            quantity = (50..150).random()
        )

        plantDao.insertAll(plant, plant2)
    }

}