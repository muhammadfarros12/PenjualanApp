package com.farroos.penjualanapp.ui.agent.search

import com.farroos.penjualanapp.data.model.agen.ResponseDataAgent

interface AgentSearchContract {

    interface Presenter {
        fun getAgent()
        fun searchAgent(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseDataAgent: ResponseDataAgent)
    }

}