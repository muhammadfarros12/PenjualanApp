package com.farroos.penjualanapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import com.farroos.penjualanapp.data.model.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {
        supportActionBar!!.title = "Masuk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        btn_login.setOnClickListener {
            presenter.doLogin(edt_username.text.toString(), edt_password.text.toString())
        }
    }

    override fun onResult(responseLogin: ResponseLogin){
        presenter.setPrefs(prefsManager,responseLogin.pegawai!!)
        finish()
    }

    override fun onLoading(loading: Boolean){
        when(loading){
            true -> {
                progress.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    override fun showMessage(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}