package com.wa.shopgoblin.data.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wa.shopgoblin.data.database.AppDatabase.Companion.usersTable

@Entity(tableName = usersTable)
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)