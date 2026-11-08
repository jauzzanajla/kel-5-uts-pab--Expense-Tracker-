package com.example.expensetracker

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.data.AppDatabase011
import com.example.expensetracker.data.SessionManager034
import com.example.expensetracker.data.Transaksi001
import com.example.expensetracker.data.TransaksiDao001
import com.example.expensetracker.databinding.ActivityFormBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FormActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID034 = "extra_id034"
    }

    private lateinit var b034: ActivityFormBinding
    private lateinit var sesi034: SessionManager034
    private lateinit var dao034: TransaksiDao001
    private var lama034: Transaksi001? = null
    private var tanggal034: Long = System.currentTimeMillis()
    private val fmt034 = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b034 = ActivityFormBinding.inflate(layoutInflater)
        setContentView(b034.root)
        sesi034 = SessionManager034(this)
        dao034 = AppDatabase011.get011(this).transaksiDao001()

        b034.btnKembali034.setOnClickListener { finish() }
        b034.rbKeluar034.isChecked = true
        tampilTanggal034()
        b034.tvTanggal034.setOnClickListener { pilihTanggal034() }
        b034.btnSimpan034.setOnClickListener { simpan034() }
        b034.btnHapus034.setOnClickListener { konfirmasiHapus034() }

        val id034 = intent.getLongExtra(EXTRA_ID034, -1L)
        if (id034 != -1L) {
            b034.tvJudul034.text = "Ubah Transaksi"
            b034.btnHapus034.visibility = View.VISIBLE
            lifecycleScope.launch {
                val t034 = dao034.ambil001(id034)
                if (t034 == null || t034.userId001 != sesi034.userId034()) {
                    finish()
                    return@launch
                }
                lama034 = t034
                if (t034.jenis001 == "MASUK") b034.rbMasuk034.isChecked = true
                else b034.rbKeluar034.isChecked = true
                b034.etJumlah034.setText(t034.jumlah001.toString())
                b034.etKategori034.setText(t034.kategori001)
                b034.etKeterangan034.setText(t034.keterangan001)
                tanggal034 = t034.tanggal001
                tampilTanggal034()
            }
        }
    }

    private fun tampilTanggal034() {
        b034.tvTanggal034.text = fmt034.format(Date(tanggal034))
    }

    private fun pilihTanggal034() {
        val kal034 = Calendar.getInstance().apply { timeInMillis = tanggal034 }
        DatePickerDialog(this, { _, tahun, bulan, hari ->
            kal034.set(tahun, bulan, hari)
            tanggal034 = kal034.timeInMillis
            tampilTanggal034()
        }, kal034.get(Calendar.YEAR), kal034.get(Calendar.MONTH), kal034.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun simpan034() {
        val jumlah034 = b034.etJumlah034.text.toString().toLongOrNull()
        val kategori034 = b034.etKategori034.text.toString().trim()
        val keterangan034 = b034.etKeterangan034.text.toString().trim()
        if (jumlah034 == null || jumlah034 <= 0L) {
            Toast.makeText(this, "Jumlah harus lebih dari 0", Toast.LENGTH_SHORT).show()
            return
        }
        if (kategori034.isEmpty()) {
            Toast.makeText(this, "Kategori wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }
        val jenis034 = if (b034.rbMasuk034.isChecked) "MASUK" else "KELUAR"
        b034.btnSimpan034.isEnabled = false
        lifecycleScope.launch {
            val lama = lama034
            if (lama == null) {
                dao034.tambah001(
                    Transaksi001(
                        userId001 = sesi034.userId034(),
                        jenis001 = jenis034,
                        jumlah001 = jumlah034,
                        kategori001 = kategori034,
                        keterangan001 = keterangan034,
                        tanggal001 = tanggal034
                    )
                )
            } else {
                dao034.ubah001(
                    lama.copy(
                        jenis001 = jenis034,
                        jumlah001 = jumlah034,
                        kategori001 = kategori034,
                        keterangan001 = keterangan034,
                        tanggal001 = tanggal034
                    )
                )
            }
            Toast.makeText(this@FormActivity, "Transaksi tersimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun konfirmasiHapus034() {
        val lama = lama034 ?: return
        AlertDialog.Builder(this)
            .setMessage("Hapus transaksi ini?")
            .setPositiveButton("Hapus") { _, _ ->
                lifecycleScope.launch {
                    dao034.hapus001(lama)
                    finish()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
