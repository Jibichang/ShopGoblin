package com.wa.shopgoblin.data.database.plant

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wa.shopgoblin.data.database.AppDatabase.Companion.plantsTable

@Dao
interface PlantDao {

    @Query("SELECT * FROM $plantsTable")
    fun getAll(): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE id IN (:plantIds)")
    fun loadAllByIds(plantIds: IntArray): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE spacial == 1")
    fun loadAllSpecial(): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE favorite == 1")
    fun loadAllFavorite(): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE id == (:id) LIMIT 1")
    fun findById(id: Int): Plant?

    @Update(entity = Plant::class)
    fun update(plant: Plant)

    @Insert
    fun insertAll(vararg plant: Plant)

    @Delete
    fun delete(plant: Plant)
}