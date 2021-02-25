package com.farroos.penjualanapp.ui.transaction.detail

import com.farroos.penjualanapp.data.model.ResponseDetailTransaction

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionByInvoice(invoice: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseDetailTransaction: ResponseDetailTransaction)
        fun onMsg(msg:String)
    }

}