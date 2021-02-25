package com.farroos.penjualanapp.ui.product

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.product.DataProduct
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_product.view.*
import kotlin.collections.ArrayList

class ProductAdapter(
    val context: Context, var product: ArrayList<DataProduct>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(product: DataProduct) {
            view.txt_name.text = product.nama_produk
            view.txt_price.text = product.harga_rupiah
            view.txt_stock.text = "Stok tersedia ${product.stok}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("data",product[position].toString())
        holder.bing(product[position])
        GlideHelper.setImage(context, product[position].gambar_produk!!, holder.view.img_image)
        holder.view.linProduct.setOnClickListener {
            com.farroos.penjualanapp.data.Constant.PRODUCT_ID = product[position].kd_produk!!
            com.farroos.penjualanapp.data.Constant.PRODUCT_NAME = product[position].nama_produk!!
            com.farroos.penjualanapp.data.Constant.IS_CHANGED = true
            (context as Activity).finish()
        }
    }

    override fun getItemCount(): Int = product.size

    fun setData(newProduk: List<DataProduct>) {
        product.clear()
        product.addAll(newProduk)
        notifyDataSetChanged()
    }

}