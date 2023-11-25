package com.wa.shopgoblin.ui.main.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.PlantDao
import com.wa.shopgoblin.data.database.plant.randFloat
import com.wa.shopgoblin.util.readJsonFromAssets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okio.buffer
import okio.source
import java.lang.reflect.Type
import java.nio.charset.Charset

class PlantViewModel(private val plantDao: PlantDao) : ViewModel() {

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList: StateFlow<List<Plant>> = _plantList

    private val _plantDetail: MutableStateFlow<Plant?> = MutableStateFlow(null)
    val plantDetail: StateFlow<Plant?> = _plantDetail

    val specialPlantList: Flow<List<Plant>> = _plantList.map {
        it.filter { plant -> plant.spacial }
    }

    fun getPlantList() {
        CoroutineScope(Dispatchers.IO).launch {
            val plants = plantDao.getAll()
            _plantList.value = plants
        }
    }

    fun savePlantList(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val plants = plantDao.getAll()
            if (plants.isEmpty()) {
                getDataFromJson(context)
            }
        }
    }

    fun getPlant(id: Int?) {
        id?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val plants = plantDao.findById(it)
                println("---------------- plantDao $plants")
                _plantDetail.value = plants
            }
        }
    }

    fun saveFavorite(plant: Plant, checked: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            plantDao.update(plant.copy(favorite = checked))
            getPlantList()
        }
    }

    private fun getDataFromJson(context: Context) {
        val data = readJsonFromAssets(
            context,
            "plants.json"
        )

        val type: Type = object : TypeToken<List<Plant?>?>() {}.type
        if (data != null) {
            val plantEntities = Gson().fromJson<List<Plant?>?>(data, type)
            plantEntities.onEach { plants ->
                plants?.let { plant ->
                    plantDao.insertAll(plant)
                }
            }
        }
    }

    private fun insertPlants() {
        val plant2 = Plant(
            name = "Cereus jamacaru",
            icon = "plant_12",
            description = "Cereus jamacaru, commonly referred to as the Mandacaru cactus, features a tall, branching stem covered in prominent ribs and sharp spines. Native to South America, this cactus species showcases nocturnal white flowers and eventually produces edible red or purplish fruits. Its imposing stature and adaptability to arid environments make it a distinctive presence in landscapes and natural habitats across various regions.",
            rating = String.format("%.2f", randFloat(3f, 5f)),
            reviewer = (1000..5000).random(),
            price = String.format("%.0f", randFloat(20f, 50f)).toDouble(),
            spacial = (1..19).random() %2 == 0,
            quantity = (50..150).random()
        )

        plantDao.insertAll(plant2)
    }

}