package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.SessionManager034
import com.example.expensetracker.databinding.ActivityAkunBinding
import kotlinx.coroutines.launch

class AkunActivity : AppCompatActivity() {
    private lateinit var b011: ActivityAkunBinding
    private lateinit var sesi011: SessionManager034

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b011 = ActivityAkunBinding.inflate(layoutInflater)
        setContentView(b011.root)
        sesi011 = SessionManager034(this)
        b011.tvNamaAkun011.text = sesi011.nama034()
        b011.btnLogout011.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Yakin ingin keluar?")
                .setPositiveButton("Keluar") { _, _ ->
                    sesi011.logout034()
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                }
                .setNegativeButton("Batal", null)
                .show()
        }
        NavHelper155.pasang155(this, b011.bottomNavAkun011, R.id.nav_akun155)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val n011 = AppDatabase011.get011(this@AkunActivity).transaksiDao001().jumlah001(sesi011.userId034())
            b011.tvJumlahAkun011.text = "$n011 transaksi tercatat"
        }
    }
}
