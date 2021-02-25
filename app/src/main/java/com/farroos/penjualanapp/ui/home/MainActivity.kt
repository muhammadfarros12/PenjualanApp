package com.farroos.penjualanapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import com.farroos.penjualanapp.ui.agent.AgentActivity
import com.farroos.penjualanapp.ui.login.LoginActivity
import com.farroos.penjualanapp.ui.transaction.TransactionActivity
import com.farroos.penjualanapp.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefsManager = PrefsManager(this)
        presenter = MainPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        when (prefsManager.prefIsLogin){
            true -> {
                crv_profil.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false -> {
                crv_profil.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    override fun initListener() {

        crv_transaction.setOnClickListener {
            if (prefsManager.prefIsLogin) {
                startActivity(Intent(this, TransactionActivity::class.java))
            } else {
                showMessage("Maaf Anda belum Login")
            }
        }

        crv_agen.setOnClickListener {
            if (prefsManager.prefIsLogin) {
                startActivity(Intent(this, AgentActivity::class.java))
            } else {
                showMessage("Maaf Anda belum Login")
            }
        }

        crv_profil.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))}

        btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
