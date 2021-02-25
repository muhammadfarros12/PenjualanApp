package com.farroos.penjualanapp.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.cart.DataCart
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_cart.view.*
import kotlinx.android.synthetic.main.adapter_cart.view.txt_category
import kotlinx.android.synthetic.main.adapter_cart.view.txt_name_produk
import kotlinx.android.synthetic.main.adapter_cart.view.txt_price
import kotlinx.android.synthetic.main.adapter_transaction.view.txt_total
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(
    val context: Context, var cart: ArrayList<DataCart>,
    val clickListener: (DataCart, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(cart: DataCart) {
            view.txt_category.text = cart.kategori
            view.txt_name_produk.text = cart.nama_produk
            view.txt_price.text = "${cart.harga_rupiah} x ${cart.jumlah}"

            val total: Double = cart!!.jumlah!!.toDouble() * cart.harga!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMAN).format(total)
            view.txt_total.text = "Rp. $totalRupiah"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(cart[position])
        GlideHelper.setImage(context, cart[position].gambar_produk!!, holder.view.img_image)
        holder.view.img_delete.setOnClickListener {
            clickListener(cart[position], position)
            removeCart(position)
        }

    }

    override fun getItemCount(): Int = cart.size

    fun setData(newCart: List<DataCart>) {
        cart.clear()
        cart.addAll(newCart)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int){
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeAll(){
        cart.clear()
        notifyDataSetChanged()
    }


}