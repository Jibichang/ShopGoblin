package com.wa.shopgoblin.data.database.plant

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wa.shopgoblin.data.database.AppDatabase.Companion.plantsTable

@Dao
interface PlantDao {

    @Query("SELECT * FROM $plantsTable")
    fun getAll(): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE id IN (:plantIds)")
    fun loadAllByIds(plantIds: IntArray): List<Plant>

    @Query("SELECT * FROM $plantsTable WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Plant?

    @Insert
    fun insertAll(vararg users: Plant)

    @Delete
    fun delete(user: Plant)
}