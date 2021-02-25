package com.farroos.penjualanapp.ui.cart

import com.farroos.penjualanapp.data.model.cart.ResponseCartList
import com.farroos.penjualanapp.data.model.cart.ResponseCartUpdate

interface CartContract {

    interface Presenter {
        fun getCart(username: String)

        fun deleteItemCart(kd_keranjang: Long)
        fun deleteCart(username: String)

        fun checkOut(username: String, kd_agen: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(responseCartList: ResponseCartList)
        fun showMessage(message: String)

        fun onResultDelete(responseCartUpdate: ResponseCartUpdate)
        fun showDialog()

        fun onLoadingCheckout(loading: Boolean)
        fun onResultCheckout(responseCheckout: ResponseCheckout)
    }

}