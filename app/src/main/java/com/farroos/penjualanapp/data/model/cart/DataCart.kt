package com.farroos.penjualanapp.data.model.cart

import com.google.gson.annotations.SerializedName

data class  DataCart(

    @SerializedName("kd_keranjang")
    val kd_keranjang: Long?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("kd_produk")
    val kd_produk: String?,
    @SerializedName("jumlah")
    val jumlah: String?,
    @SerializedName("harga")
    val harga: String?,
    @SerializedName("harga_rupiah")
    val harga_rupiah: String?,
    @SerializedName("nama_produk")
    val nama_produk: String?,
    @SerializedName("kategori")
    val kategori: String?,
    @SerializedName("gambar_produk")
    val gambar_produk: String?

)
