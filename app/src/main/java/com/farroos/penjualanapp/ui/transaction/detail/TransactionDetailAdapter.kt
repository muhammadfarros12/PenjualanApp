package com.farroos.penjualanapp.ui.transaction.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.DataItem
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class   TransactionDetailAdapter(
    val context: Context, var detail: ArrayList<DataItem>):
    RecyclerView.Adapter<TransactionDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(detail: DataItem) {
            view.txt_category.text = detail.kategori
            view.txt_name_produk.text = detail.namaProduk
            view.txt_price.text = "${detail.hargaRupiah} x ${detail.jumlah} "

            val total: Double = detail!!.jumlah!!.toDouble() * detail.harga!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMAN).format(total)
            view.txt_total.text = "Rp. $totalRupiah"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction_detail, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(detail[position])
        GlideHelper.setImage(context, detail[position].gambarProduk!!, holder.view.img_image)

    }

    override fun getItemCount(): Int = detail.size

    fun setData(newDetail: List<DataItem>) {
        detail.clear()
        detail.addAll(newDetail)
        notifyDataSetChanged()
    }

}