package com.farroos.penjualanapp.ui.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farroos.penjualanapp.R

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        iniActivity()
    }

    fun iniActivity() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TransactionFragment(),"tag_fragment")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.findFragmentByTag("tag_fragment") == null ) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransactionFragment(), "tag_fragment")
                .commit()
        }else {
            finish()
        }

        return super.onSupportNavigateUp()
    }

}