package com.wa.shopgoblin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.PlantDao
import com.wa.shopgoblin.data.database.user.User
import com.wa.shopgoblin.data.database.user.UserDao

@Database(entities = [User::class, Plant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun plantDao(): PlantDao

    companion object {
        const val usersTable = "users"
        const val plantsTable = "plants"
    }
}