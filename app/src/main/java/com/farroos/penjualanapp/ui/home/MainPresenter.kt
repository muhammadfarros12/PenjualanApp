package com.farroos.penjualanapp.ui.home

class MainPresenter (val view: MainContract.View) {

    init {
        view.initListener()
    }

}