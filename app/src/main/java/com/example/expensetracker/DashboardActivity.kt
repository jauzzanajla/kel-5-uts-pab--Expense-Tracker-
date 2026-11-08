package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.SessionManager034
import com.example.expensetracker.databinding.ActivityDashboardBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class DashboardActivity : AppCompatActivity() {
    private lateinit var b034: ActivityDashboardBinding
    private lateinit var sesi034: SessionManager034
    private val locale034 = Locale.forLanguageTag("id-ID")
    private val fmtNum034 = NumberFormat.getInstance(locale034)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b034 = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(b034.root)
        sesi034 = SessionManager034(this)
        b034.tvSalam034.text = "Halo, ${sesi034.nama034()}"
        b034.btnLihat034.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }
        b034.btnTambahCepat034.setOnClickListener { startActivity(Intent(this, FormActivity::class.java)) }
        NavHelper155.pasang155(this, b034.bottomNav034, R.id.nav_beranda155)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            try {
                val dao034 = AppDatabase011.get011(this@DashboardActivity).transaksiDao001()
                val uid034 = sesi034.userId034()
                val masuk034 = dao034.totalMasuk001(uid034)
                val keluar034 = dao034.totalKeluar001(uid034)
                val saldo034 = masuk034 - keluar034
                b034.tvSaldo034.text = "Rp ${fmtNum034.format(saldo034)}"
                b034.tvSaldo034.setTextColor(if (saldo034 < 0) 0xFFFF3B30.toInt() else 0xFF1C1C1E.toInt())
                b034.tvMasuk034.text = "Rp ${fmtNum034.format(masuk034)}"
                b034.tvKeluar034.text = "Rp ${fmtNum034.format(keluar034)}"
                b034.tvJumlah034.text = "${dao034.jumlah001(uid034)} transaksi tercatat"
            } catch (e034: Exception) {
                Toast.makeText(this@DashboardActivity, "Terjadi kesalahan: ${e034.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
