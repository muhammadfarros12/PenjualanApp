package com.farroos.penjualanapp.data.model.transaction

import com.google.gson.annotations.SerializedName

data class ResponseTransactionList(

    @SerializedName("status")
    val status: Boolean,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("data")
    val dataTransaction: List<DataTransaction>

)
