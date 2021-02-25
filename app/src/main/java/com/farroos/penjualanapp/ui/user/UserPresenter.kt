package com.farroos.penjualanapp.ui.user

import com.farroos.penjualanapp.data.database.PrefsManager

class UserPresenter (val view: UserContract.View): UserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManager: PrefsManager) {
        if (prefsManager.prefIsLogin) view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showMessage("Berhasil Keluar")
        view.onResultLogout()
    }

}