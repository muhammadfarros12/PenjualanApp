package com.farroos.penjualanapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        prefsManager = PrefsManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefsManager)
    }

    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btn_back.setOnClickListener {
            finish()
        }
        txt_logout.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {
        txt_username.text = prefsManager.prefsUsername
        txt_nama.text = prefsManager.prefsNamaPegawai
        txt_gender.text = prefsManager.prefsJk
        txt_address.text = prefsManager.prefsAlamat
    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}