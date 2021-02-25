package com.farroos.penjualanapp.ui.home

interface MainContract {

    interface View{
        fun initListener()
        fun showMessage(message: String)
    }

}