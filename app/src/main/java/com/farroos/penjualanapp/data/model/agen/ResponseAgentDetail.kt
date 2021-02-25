package com.farroos.penjualanapp.data.model.agen

import com.google.gson.annotations.SerializedName

data class ResponseAgentDetail(

    @SerializedName("status")
    val status: Boolean,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("data")
    val dataAgent: DataTransaction

)
