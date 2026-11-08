package com.example.expensetracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransaksiDao001 {
    @Insert
    suspend fun tambah001(t: Transaksi001): Long

    @Update
    suspend fun ubah001(t: Transaksi001)

    @Delete
    suspend fun hapus001(t: Transaksi001)

    @Query("SELECT * FROM transaksi001 WHERE id001 = :id LIMIT 1")
    suspend fun ambil001(id: Long): Transaksi001?

    @Query("SELECT * FROM transaksi001 WHERE userId001 = :uid AND (:jenis = 'SEMUA' OR jenis001 = :jenis) AND (keterangan001 LIKE '%' || :cari || '%' OR kategori001 LIKE '%' || :cari || '%') ORDER BY tanggal001 DESC, id001 DESC")
    suspend fun daftar001(uid: Long, jenis: String, cari: String): List<Transaksi001>

    @Query("SELECT COALESCE(SUM(jumlah001), 0) FROM transaksi001 WHERE userId001 = :uid AND jenis001 = 'MASUK'")
    suspend fun totalMasuk001(uid: Long): Long

    @Query("SELECT COALESCE(SUM(jumlah001), 0) FROM transaksi001 WHERE userId001 = :uid AND jenis001 = 'KELUAR'")
    suspend fun totalKeluar001(uid: Long): Long

    @Query("SELECT COUNT(*) FROM transaksi001 WHERE userId001 = :uid")
    suspend fun jumlah001(uid: Long): Int
}
