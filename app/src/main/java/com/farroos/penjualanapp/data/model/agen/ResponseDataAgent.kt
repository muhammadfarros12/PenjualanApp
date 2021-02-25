package com.farroos.penjualanapp.data.model.agen

import com.google.gson.annotations.SerializedName

data class ResponseDataAgent(

    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val dataAgent: List<DataTransaction>

)
