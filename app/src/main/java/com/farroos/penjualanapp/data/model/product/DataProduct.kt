package com.farroos.penjualanapp.data.model.product

import com.google.gson.annotations.SerializedName

data class DataProduct(

    @SerializedName("kd_produk")
    val kd_produk: Long?,
    @SerializedName("kd_kategori")
    val kd_kategori: String?,
    @SerializedName("nama_produk")
    val nama_produk: String?,
    @SerializedName("harga")
    val harga: String?,
    @SerializedName("harga_rupiah")
    val harga_rupiah: String?,
    @SerializedName("gambar_produk")
    val gambar_produk: String?,
    @SerializedName("stok")
    val stok: String?,
    @SerializedName("kategori")
    val kategori: String?

)
