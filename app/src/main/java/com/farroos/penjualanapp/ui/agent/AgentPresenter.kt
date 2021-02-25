package com.farroos.penjualanapp.ui.agent

import com.farroos.penjualanapp.data.model.agen.ResponseAgentUpdate
import com.farroos.penjualanapp.data.model.agen.ResponseDataAgent
import com.farroos.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentPresenter (var view: AgentContract.View): AgentContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endpoint.getAgent()
            .enqueue(object : Callback<ResponseDataAgent>{
                override fun onResponse(
                    call: Call<ResponseDataAgent>,
                    response: Response<ResponseDataAgent>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful){
                        val responseDataAgen: ResponseDataAgent? = response.body()
                        view.onResultAgent(responseDataAgen!!)
                    }
                }

                override fun onFailure(call: Call<ResponseDataAgent>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

            })
    }

    override fun deleteAgent(kd_agen: Long) {
        view.onLoadingAgent(true)
        ApiService.endpoint.deleteAgen(kd_agen)
            .enqueue(object : Callback<ResponseAgentUpdate>{
                override fun onResponse(
                    call: Call<ResponseAgentUpdate>,
                    response: Response<ResponseAgentUpdate>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                        view.onResultDelete(responseAgentUpdate!!)
                    }
                }

                override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

            })
    }
}