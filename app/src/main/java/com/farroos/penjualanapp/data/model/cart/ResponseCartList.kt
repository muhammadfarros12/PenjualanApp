package com.farroos.penjualanapp.data.model.cart

import com.google.gson.annotations.SerializedName

data class ResponseCartList(

    @SerializedName("status")
    val status: Boolean,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("data")
    val dataCart: List<DataCart>

)
