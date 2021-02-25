package com.farroos.penjualanapp.ui.agent.update

import com.farroos.penjualanapp.data.model.agen.ResponseAgentDetail
import com.farroos.penjualanapp.data.model.agen.ResponseAgentUpdate
import com.farroos.penjualanapp.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File
import retrofit2.Callback
import retrofit2.Response

class AgentUpdatePresenter(val view: AgentUpdateContract.View): AgentUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getDetail(kd_agen: Long) {
        view.onLoading(true)
        ApiService.endpoint.getAgenDetail(kd_agen)
            .enqueue(object : Callback<ResponseAgentDetail>{
                override fun onResponse(
                    call: Call<ResponseAgentDetail>,
                    response: Response<ResponseAgentDetail>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responseAgentDetail: ResponseAgentDetail? = response.body()
                        view.onResultDetail(responseAgentDetail!!)
                    }
                }

                override fun onFailure(call: Call<ResponseAgentDetail>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }

    override fun updateAgent(
        kd_agen: Long,
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambar_toko: File?
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part

        if (gambar_toko != null){
            requestBody = RequestBody.create(MediaType.parse("image/*"), gambar_toko)
            multipartBody = MultipartBody.Part.createFormData("gambar_toko",
                gambar_toko.name, requestBody)
        } else {
            requestBody = RequestBody.create(MediaType.parse("image/*"), "")
            multipartBody = MultipartBody.Part.createFormData("gambar_toko",
                "", requestBody)
        }

        view.onLoading(true)
        ApiService.endpoint.updateAgent(kd_agen, nama_toko, nama_pemilik, alamat, latitude, longitude,
        multipartBody,"PUT")
            .enqueue(object : Callback<ResponseAgentUpdate>{
                override fun onResponse(
                    call: Call<ResponseAgentUpdate>,
                    response: Response<ResponseAgentUpdate>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                        view.onResultUpdate(responseAgentUpdate!!)
                    }
                }

                override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                    view.onLoading(false)
                }

            })

    }
}