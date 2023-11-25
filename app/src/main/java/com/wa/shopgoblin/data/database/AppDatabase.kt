package com.wa.shopgoblin.data.database

import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.data.database.plant.PlantDao
import com.wa.shopgoblin.data.database.user.User
import com.wa.shopgoblin.data.database.user.UserDao

@Database(
    entities = [User::class, Plant::class],
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2, spec = AppDatabase.MyAutoMigration::class)
//    ],
//    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun plantDao(): PlantDao

    @DeleteColumn(tableName = plantsTable, columnName = "icon")
    class MyAutoMigration : AutoMigrationSpec { }

    companion object {
        const val usersTable = "users"
        const val plantsTable = "plants"

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE $plantsTable ADD COLUMN icons TEXT NOT NULL DEFAULT ''" )
                database.execSQL("ALTER TABLE $plantsTable DROP COLUMN icon" )
                database.execSQL("ALTER TABLE icons RENAME TO icon")
            }
        }
    }
}