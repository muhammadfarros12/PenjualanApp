package com.farroos.penjualanapp.data.model.login

import com.google.gson.annotations.SerializedName

data class DataLogin(

    @SerializedName( "username")
    val username : String?,
    @SerializedName( "password")
    val password : String?,
    @SerializedName( "nama_pegawai")
    val nama_pegawai : String?,
    @SerializedName( "jk")
    val jk : String?,
    @SerializedName( "alamat")
    val alamat : String?,
    @SerializedName( "is_aktif")
    val is_aktif : String?

)

/**
"status": true,
"msg": "Username ditemukan",
"pegawai": {
"username": "rasyid",
"password": "$2y$10$lYqrQAxc1Z/0BKNjvdR0DuAyC86gWai1Ju967oUqcANn1L79u7gfW",
"nama_pegawai": "rasyid nugraha",
"jk": "PRIA",
"alamat": "batam",
"is_aktif": "1"*/