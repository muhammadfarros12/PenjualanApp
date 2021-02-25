package com.farroos.penjualanapp.ui.agent.search

import com.farroos.penjualanapp.data.model.agen.ResponseDataAgent
import com.farroos.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentSearchPresenter(val view: AgentSearchContract.View) : AgentSearchContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endpoint.getAgent()
            .enqueue(object : Callback<ResponseDataAgent> {
                override fun onResponse(
                    call: Call<ResponseDataAgent>,
                    response: Response<ResponseDataAgent>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseDataAgent: ResponseDataAgent? = response.body()
                        responseDataAgent?.let {
                            view.onResultAgent(it)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDataAgent>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

            })
    }

    override fun searchAgent(keyword: String) {
        view.onLoadingAgent(true)
        ApiService.endpoint.searchAgent(keyword)
            .enqueue(object : Callback<ResponseDataAgent> {
                override fun onResponse(
                    call: Call<ResponseDataAgent>,
                    response: Response<ResponseDataAgent>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseDataAgent: ResponseDataAgent? = response.body()
                        responseDataAgent?.let {
                            view.onResultAgent(it)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDataAgent>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

            })
    }
}