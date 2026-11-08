package com.example.expensetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao021 {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun tambah021(user: User021): Long

    @Query("SELECT * FROM user021 WHERE username021 = :username LIMIT 1")
    suspend fun cari021(username: String): User021?
}
