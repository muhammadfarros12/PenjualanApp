package com.farroos.penjualanapp.ui.product

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.product.DataCategory
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_category.view.*
import kotlin.collections.ArrayList

class CategoryAdapter(
    val context: Context, var category: ArrayList<DataCategory>,
    val clickListener: (DataCategory, Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(category: DataCategory) {
            view.txt_category.text = category.kategori
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_category,
            parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("data",category[position].toString())
        holder.bing(category[position])
        GlideHelper.setImage(context, category[position].gambar_kategori!!, holder.view.img_image)
        holder.view.relCategory.setOnClickListener {
            com.farroos.penjualanapp.data.Constant.CATEGORY_ID = category[position].kd_kategori!!
            clickListener(category[position], position)
        }
    }

    override fun getItemCount(): Int = category.size

    fun setData(newCategory: List<DataCategory>) {
        category.clear()
        category.addAll(newCategory)
        notifyDataSetChanged()
    }

}