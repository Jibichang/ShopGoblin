package com.wa.shopgoblin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wa.shopgoblin.data.database.user.User
import com.wa.shopgoblin.data.database.user.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}