package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.SessionManager034
import com.example.expensetracker.databinding.ActivityListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    private lateinit var b155: ActivityListBinding
    private lateinit var sesi155: SessionManager034
    private lateinit var adapter155: TransaksiAdapter155
    private val pilihan155 = arrayOf("Semua", "Pemasukan", "Pengeluaran")
    private var jobMuat155: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b155 = ActivityListBinding.inflate(layoutInflater)
        setContentView(b155.root)
        sesi155 = SessionManager034(this)

        adapter155 = TransaksiAdapter155 { t155 ->
            startActivity(
                Intent(this, FormActivity::class.java).putExtra(FormActivity.EXTRA_ID034, t155.id001)
            )
        }
        b155.rv155.layoutManager = LinearLayoutManager(this)
        b155.rv155.adapter = adapter155

        b155.spFilter155.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pilihan155)
        b155.spFilter155.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                muat155()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        b155.etCari155.doAfterTextChanged { muat155() }
        b155.btnTambah155.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
        NavHelper155.pasang155(this, b155.bottomNav155, R.id.nav_transaksi155)
    }

    override fun onResume() {
        super.onResume()
        muat155()
    }

    private fun muat155() {
        val jenis155 = when (b155.spFilter155.selectedItemPosition) {
            1 -> "MASUK"
            2 -> "KELUAR"
            else -> "SEMUA"
        }
        val cari155 = b155.etCari155.text.toString().trim()
        jobMuat155?.cancel()
        jobMuat155 = lifecycleScope.launch {
            val hasil155 = AppDatabase011.get011(this@ListActivity).transaksiDao001()
                .daftar001(sesi155.userId034(), jenis155, cari155)
            adapter155.setData155(hasil155)
            b155.tvKosong155.visibility = if (hasil155.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
