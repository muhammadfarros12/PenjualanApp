package com.farroos.penjualanapp.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import com.farroos.penjualanapp.data.model.cart.DataCart
import com.farroos.penjualanapp.data.model.cart.ResponseCartList
import com.farroos.penjualanapp.data.model.cart.ResponseCartUpdate
import com.farroos.penjualanapp.ui.agent.search.AgentSearchActivity
import com.farroos.penjualanapp.ui.cart.add.CartAddActivity
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.swipe

class CartActivity : AppCompatActivity(), CartContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        prefsManager = PrefsManager(this)
        cartPresenter = CartPresenter(this)
        cartPresenter.getCart(prefsManager.prefsUsername)
    }

    override fun onStart() {
        super.onStart()
        if (com.farroos.penjualanapp.data.Constant.IS_CHANGED) {
            com.farroos.penjualanapp.data.Constant.IS_CHANGED = false
            cartPresenter.getCart(prefsManager.prefsUsername)
            edt_agen.setText(com.farroos.penjualanapp.data.Constant.AGENT_NAME)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        com.farroos.penjualanapp.data.Constant.AGENT_NAME = ""

    }

    override fun initActivity() {
        supportActionBar!!.title = "Keranjang"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cartAdapter = CartAdapter(this, arrayListOf()) { dataCart: DataCart, position: Int ->
            cartPresenter.deleteItemCart(dataCart.kd_keranjang!!)
        }

    }

    override fun initListener() {
        txt_reset.visibility = View.GONE
        edt_agen.visibility = View.GONE

        rcv_cart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        swipe.setOnRefreshListener {
            cartPresenter.getCart(prefsManager.prefsUsername)
        }

        btn_add.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }

        txt_reset.setOnClickListener {
            showDialog()
        }

        edt_agen.setOnClickListener {
            startActivity(Intent(this, AgentSearchActivity::class.java))
        }

        btn_checkout.setOnClickListener {

            if (cartAdapter.cart.isNullOrEmpty()) {
                showMessage("Keranjang kosong")
            } else {
                if (edt_agen.text.isNullOrEmpty()) {
                    edt_agen.error = "Tidak boleh kosong"
                } else {
                    cartPresenter.checkOut(prefsManager.prefsUsername, com.farroos.penjualanapp.data.Constant.AGENT_ID)
                }
            }

        }
    }

    override fun onLoadingCart(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false

        }
    }

    override fun onResultCart(responseCartList: ResponseCartList) {
        val dataCart: List<DataCart> = responseCartList.dataCart
        if (dataCart.isNullOrEmpty()) {
            txt_reset.visibility = View.GONE
            edt_agen.visibility = View.GONE
        } else {
            cartAdapter.setData(dataCart)
            txt_reset.visibility = View.VISIBLE
            edt_agen.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDelete(responseCartUpdate: ResponseCartUpdate) {
        cartPresenter.getCart(prefsManager.prefsUsername)
        cartAdapter.removeAll()
    }

    override fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Hapus Semua item dalam keranjang?")
        dialog.setPositiveButton("Hapus") { dialog, which ->
            cartPresenter.deleteCart(prefsManager.prefsUsername)
            dialog.dismiss()
        }
        dialog.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onLoadingCheckout(loading: Boolean) {
        when (loading) {
            true -> {
                btn_checkout.isEnabled = false
                btn_checkout.setText("Memuat...")
            }
            false -> {
                btn_checkout.isEnabled = true
                btn_checkout.setText("Checkout")
            }
        }
    }

    override fun onResultCheckout(responseCheckout: ResponseCheckout) {
        cartPresenter.getCart(prefsManager.prefsUsername)
        cartAdapter.removeAll()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}