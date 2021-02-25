package com.farroos.penjualanapp.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import com.farroos.penjualanapp.data.model.cart.ResponseCartUpdate
import com.farroos.penjualanapp.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    lateinit var cartAddPresenter: CartAddPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)
        prefsManager = PrefsManager(this)
        cartAddPresenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if (com.farroos.penjualanapp.data.Constant.IS_CHANGED) {
            com.farroos.penjualanapp.data.Constant.IS_CHANGED = false
            edtProduct.setText(com.farroos.penjualanapp.data.Constant.PRODUCT_NAME)
            txvQty.visibility = View.VISIBLE
            npQuantity.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        com.farroos.penjualanapp.data.Constant.PRODUCT_ID = 0
        com.farroos.penjualanapp.data.Constant.PRODUCT_NAME = ""
        com.farroos.penjualanapp.data.Constant.PRODUCT_QTY = 0
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        txvQty.visibility = View.GONE
        npQuantity.visibility = View.GONE
    }

    override fun initListener() {
        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        npQuantity.minValue = 1
        npQuantity.maxValue = 500
        npQuantity.wrapSelectorWheel = true
        npQuantity.setOnValueChangedListener { picker, oldVal, newVal ->
            com.farroos.penjualanapp.data.Constant.PRODUCT_QTY = newVal.toLong()
        }

        btnSubmit.setOnClickListener {
            if (com.farroos.penjualanapp.data.Constant.PRODUCT_ID > 0) {
                com.farroos.penjualanapp.data.Constant.PRODUCT_QTY = if (com.farroos.penjualanapp.data.Constant.PRODUCT_QTY > 0) com.farroos.penjualanapp.data.Constant.PRODUCT_QTY else 1
                cartAddPresenter.addCart(
                    prefsManager.prefsUsername, com.farroos.penjualanapp.data.Constant.PRODUCT_ID, com.farroos.penjualanapp.data.Constant.PRODUCT_QTY
                )
            } else {
                edtProduct.error = "Tidak Boleh Kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSubmit.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseCartUpdate: ResponseCartUpdate) {
        if (responseCartUpdate.status) {
            com.farroos.penjualanapp.data.Constant.IS_CHANGED = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}