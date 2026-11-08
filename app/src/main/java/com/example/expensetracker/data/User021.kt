package com.example.expensetracker.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user021", indices = [Index(value = ["username021"], unique = true)])
data class User021(
    @PrimaryKey(autoGenerate = true) val id021: Long = 0,
    val username021: String,
    val passwordHash021: String
)
