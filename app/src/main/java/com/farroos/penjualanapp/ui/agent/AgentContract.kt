package com.farroos.penjualanapp.ui.agent

import com.farroos.penjualanapp.data.model.agen.DataTransaction
import com.farroos.penjualanapp.data.model.agen.ResponseAgentUpdate
import com.farroos.penjualanapp.data.model.agen.ResponseDataAgent

interface AgentContract {

    interface Presenter{
        fun getAgent()
        fun deleteAgent(kd_agen: Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseDataAgent: ResponseDataAgent)
        fun onResultDelete(responseDataUpdate: ResponseAgentUpdate)
        fun showDialogDelete(dataAgent: DataTransaction, position: Int)
        fun showDialogDetail(dataAgent: DataTransaction, position: Int)
        fun showMessage(message: String)
    }

}