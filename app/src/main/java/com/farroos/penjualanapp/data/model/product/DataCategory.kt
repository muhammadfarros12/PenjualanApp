package com.farroos.penjualanapp.data.model.product

import com.google.gson.annotations.SerializedName

data class DataCategory(

    @SerializedName("kd_kategori")
    val kd_kategori: Long?,
    @SerializedName("kategori")
    val kategori: String?,
    @SerializedName("gambar")
    val gambar_kategori: String?

)
