package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.Hash011
import com.example.expensetracker.data.SessionManager034
import com.example.expensetracker.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var b021: ActivityLoginBinding
    private lateinit var sesi021: SessionManager034

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sesi021 = SessionManager034(this)
        if (sesi021.sudahLogin034()) {
            keDashboard021()
            return
        }
        b021 = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b021.root)
        b021.btnMasuk021.setOnClickListener { masuk021() }
        b021.tvDaftar021.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun masuk021() {
        val username021 = b021.etUsername021.text.toString().trim()
        val password021 = b021.etPassword021.text.toString()
        if (username021.isEmpty() || password021.isEmpty()) {
            Toast.makeText(this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }
        b021.btnMasuk021.isEnabled = false
        lifecycleScope.launch {
            try {
                val user021 = AppDatabase011.get011(this@LoginActivity).userDao021().cari021(username021)
                if (user021 != null && user021.passwordHash021 == Hash011.sha256011(password021)) {
                    sesi021.simpan034(user021.id021, user021.username021)
                    keDashboard021()
                } else {
                    b021.btnMasuk021.isEnabled = true
                    Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            } catch (e021: Exception) {
                b021.btnMasuk021.isEnabled = true
                Toast.makeText(this@LoginActivity, "Terjadi kesalahan: ${e021.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun keDashboard021() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}
