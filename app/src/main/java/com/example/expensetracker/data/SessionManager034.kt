package com.example.expensetracker.data

import android.content.Context

class SessionManager034(context: Context) {
    private val pref034 = context.applicationContext
        .getSharedPreferences("sesi034", Context.MODE_PRIVATE)

    fun simpan034(id: Long, nama: String) {
        pref034.edit().putLong("uid034", id).putString("nama034", nama).apply()
    }
    fun userId034(): Long = pref034.getLong("uid034", -1L)
    fun nama034(): String = pref034.getString("nama034", "") ?: ""
    fun sudahLogin034(): Boolean = userId034() != -1L
    fun logout034() {
        pref034.edit().clear().apply()
    }
}
