package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User021::class, Transaksi001::class], version = 1, exportSchema = false)
abstract class AppDatabase011 : RoomDatabase() {
    abstract fun userDao021(): UserDao021
    abstract fun transaksiDao001(): TransaksiDao001

    companion object {
        @Volatile private var instance011: AppDatabase011? = null

        fun get011(context: Context): AppDatabase011 =
            instance011 ?: synchronized(this) {
                instance011 ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase011::class.java,
                    "expense011.db"
                ).fallbackToDestructiveMigration(true).build().also { instance011 = it }
            }
    }
}
