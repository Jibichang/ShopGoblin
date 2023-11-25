package com.wa.shopgoblin.di

import androidx.room.Room
import com.wa.shopgoblin.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {


    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "app_db"
        )
//            .fallbackToDestructiveMigration()
//            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

    single {
        get<AppDatabase>().userDao()
    }

    single {
        get<AppDatabase>().plantDao()
    }
}