package com.farroos.penjualanapp.ui.agent.create

import com.farroos.penjualanapp.data.model.agen.ResponseAgentUpdate
import com.farroos.penjualanapp.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File
import retrofit2.Callback
import retrofit2.Response

class AgentCreatePresenter(val view: AgentCreateContract.View): AgentCreateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertAgent(
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambar_toko: File
    ) {

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), gambar_toko)
        val multipartBody: MultipartBody.Part? = MultipartBody.Part.createFormData("gambar_toko",
            gambar_toko.name, requestBody)

        view.onLoading(true)
        ApiService.endpoint.insertAgent(nama_toko, nama_pemilik, alamat, latitude, longitude, multipartBody!!)
            .enqueue(object :Callback<ResponseAgentUpdate>{
                override fun onResponse(
                    call: Call<ResponseAgentUpdate>,
                    response: Response<ResponseAgentUpdate>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                        view.onResult(responseAgentUpdate!!)
                    }
                }

                override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                    view.onLoading(false)

                }

            })
    }

}