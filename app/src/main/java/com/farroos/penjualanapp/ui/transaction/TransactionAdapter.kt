package com.farroos.penjualanapp.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.transaction.DataTransaction
import kotlinx.android.synthetic.main.adapter_transaction.view.*

class TransactionAdapter(
    val context: Context, var transaction: ArrayList<DataTransaction>,
    val clickListener: (DataTransaction, Int) -> Unit
) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(transaction: DataTransaction) {
            view.txt_invoice.text = transaction.no_faktur
            view.txt_date.text = transaction.tgl_penjualan
            view.txt_total.text = transaction.total_rupiah
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(transaction[position])

        holder.view.txt_see.setOnClickListener {
            clickListener(transaction[position], position)
        }
    }

    override fun getItemCount(): Int = transaction.size

    fun setData(newTransaction: List<DataTransaction>) {
        transaction.clear()
        transaction.addAll(newTransaction)
        notifyDataSetChanged()
    }

}