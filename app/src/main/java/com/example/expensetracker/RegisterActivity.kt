package com.example.expensetracker

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.Hash011
import com.example.expensetracker.data.User021
import com.example.expensetracker.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var b001: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b001 = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b001.root)
        b001.btnKembali001.setOnClickListener { finish() }
        b001.btnDaftar001.setOnClickListener { daftar001() }
    }

    private fun pesan001(teks001: String) {
        Toast.makeText(this, teks001, Toast.LENGTH_SHORT).show()
    }

    private fun daftar001() {
        val username001 = b001.etUsername001.text.toString().trim()
        val password001 = b001.etPassword001.text.toString()
        val konfirmasi001 = b001.etKonfirmasi001.text.toString()
        when {
            username001.length < 3 -> { pesan001("Username minimal 3 karakter"); return }
            password001.length < 6 -> { pesan001("Password minimal 6 karakter"); return }
            password001 != konfirmasi001 -> { pesan001("Konfirmasi password tidak sama"); return }
        }
        b001.btnDaftar001.isEnabled = false
        lifecycleScope.launch {
            val dao001 = AppDatabase011.get011(this@RegisterActivity).userDao021()
            if (dao001.cari021(username001) != null) {
                b001.btnDaftar001.isEnabled = true
                pesan001("Username sudah dipakai")
                return@launch
            }
            try {
                dao001.tambah021(User021(username021 = username001, passwordHash021 = Hash011.sha256011(password001)))
                pesan001("Akun berhasil dibuat, silakan masuk")
                finish()
            } catch (e001: SQLiteConstraintException) {
                b001.btnDaftar001.isEnabled = true
                pesan001("Username sudah dipakai")
            }
        }
    }
}
