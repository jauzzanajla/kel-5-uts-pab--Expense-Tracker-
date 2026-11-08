package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

object NavHelper155 {
    fun pasang155(a155: AppCompatActivity, nav155: BottomNavigationView, aktif155: Int) {
        nav155.selectedItemId = aktif155
        nav155.setOnItemSelectedListener { item155 ->
            if (item155.itemId == aktif155) return@setOnItemSelectedListener true
            val tujuan155 = when (item155.itemId) {
                R.id.nav_beranda155 -> DashboardActivity::class.java
                R.id.nav_transaksi155 -> ListActivity::class.java
                else -> AkunActivity::class.java
            }
            val intent155 = Intent(a155, tujuan155).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            if (item155.itemId == R.id.nav_beranda155) {
                intent155.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                a155.startActivity(intent155)
            } else {
                a155.startActivity(intent155)
                if (aktif155 != R.id.nav_beranda155) a155.finish()
            }
            a155.overridePendingTransition(0, 0)
            false
        }
    }
}
