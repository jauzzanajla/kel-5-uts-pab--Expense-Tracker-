package com.example.expensetracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.Transaksi001
import com.example.expensetracker.databinding.ItemTransaksiBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransaksiAdapter155(
    private val onKlik155: (Transaksi001) -> Unit
) : RecyclerView.Adapter<TransaksiAdapter155.VH155>() {

    private val data155 = mutableListOf<Transaksi001>()
    private val locale155 = Locale.forLanguageTag("id-ID")
    private val fmt155 = SimpleDateFormat("dd MMM yyyy", locale155)
    private val fmtNum155 = NumberFormat.getInstance(locale155)

    class VH155(val b155: ItemTransaksiBinding) : RecyclerView.ViewHolder(b155.root)

    fun setData155(baru155: List<Transaksi001>) {
        data155.clear()
        data155.addAll(baru155)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH155 =
        VH155(ItemTransaksiBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = data155.size

    override fun onBindViewHolder(holder: VH155, position: Int) {
        val t155 = data155[position]
        val masuk155 = t155.jenis001 == "MASUK"
        holder.b155.tvKeterangan155.text = t155.keterangan001.ifBlank { t155.kategori001 }
        holder.b155.tvInfo155.text = "${t155.kategori001} • ${fmt155.format(Date(t155.tanggal001))}"
        holder.b155.tvNominal155.text =
            (if (masuk155) "+ " else "- ") + "Rp ${fmtNum155.format(t155.jumlah001)}"
        holder.b155.tvNominal155.setTextColor(
            if (masuk155) 0xFF34C759.toInt() else 0xFFFF3B30.toInt()
        )
        holder.b155.root.setOnClickListener { onKlik155(t155) }
    }
}
