package com.example.expensetracker.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "transaksi001", indices = [Index(value = ["userId001"])])
data class Transaksi001(
    @PrimaryKey(autoGenerate = true) val id001: Long = 0,
    val userId001: Long,
    val jenis001: String,
    val jumlah001: Long,
    val kategori001: String,
    val keterangan001: String,
    val tanggal001: Long
)
