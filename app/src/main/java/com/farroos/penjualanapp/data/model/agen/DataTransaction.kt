package com.farroos.penjualanapp.data.model.agen

import com.google.gson.annotations.SerializedName

data class DataTransaction(

    @SerializedName("kd_agen")
    val kd_agen: Long?,
    @SerializedName("nama_toko")
    val nama_toko: String?,
    @SerializedName("nama_pemilik")
    val nama_pemilik: String?,
    @SerializedName("alamat")
    val alamat: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("gambar_toko")
    val gambar_toko: String?

)
/**
"data": [
{
    "kd_agen": 2,
    "nama_toko": "Pusat Kaos Surabaya",
    "nama_pemilik": "Anton Nugraha",
    "alamat": "Jl. Gelatik No. 140, Medokan Ayu, Kec. Rungkut, Kota Surabaya, Jawa Timur",
    "latitude": "-7.331868",
    "longitude": "112.788068",
    "gambar_toko": "https://penjualanapp.webpenjualan.my.id/public/uploads/agen/20210125073643.jpg*/
