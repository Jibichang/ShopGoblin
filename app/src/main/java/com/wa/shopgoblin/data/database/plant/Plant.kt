package com.wa.shopgoblin.data.database.plant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.AppDatabase.Companion.plantsTable
import java.util.Random

@Entity(tableName = plantsTable)
data class Plant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "icon") val icon: String = "",
    @ColumnInfo(name = "rating") val rating: String = "0.0",
    @ColumnInfo(name = "reviewer") val reviewer: Int = 0,
    @ColumnInfo(name = "price") val price: Double = 0.0,
    @ColumnInfo(name = "spacial") val spacial: Boolean = false,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false,
    @ColumnInfo(name = "quantity") val quantity: Int = 100
)



val plant1 = Plant(
    name = "Sansevieria Trifasciata Snake Plant",
    icon = "plant_1",
    description = "Dracaena trifasciata is a species of flowering plant in the family Asparagaceae, native to tropical West Africa from Nigeria east to the Congo. It is most commonly known as the snake plant, Saint George's sword, mother-in-law's tongue, and viper's bowstring hemp, among other names.",
    rating = "4.5",
    reviewer = (1000..5000).random(),
    price = String.format("%.0f", randFloat(20f, 50f)).toDouble(),
    spacial = (0..1).random() == 1,
    quantity = (50..150).random()
)
val plant2 = Plant(
    name = "Chinese Money Plant",
    icon = "plant_2",
    description = "Pilea peperomioides, the Chinese money plant, UFO plant, pancake plant, lefse plant or missionary plant, is a species of flowering plant in the nettle family Urticaceae, native to Yunnan and Sichuan provinces in southern China.",
    rating = String.format("%.2f", randFloat(3f, 5f)),
    reviewer = (1000..5000).random(),
    price = String.format("%.0f", randFloat(20f, 50f)).toDouble(),
    spacial = (1..19).random() %2 == 0,
    quantity = (50..150).random()
)


fun randFloat(min: Float, max: Float): Float {
    val rand = Random()
    return rand.nextFloat() * (max - min) + min
}