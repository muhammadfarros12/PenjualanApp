package com.farroos.penjualanapp.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.product.*
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: ProdukPresenter
    private var kd_kategori: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        presenter = ProdukPresenter(this)
        presenter.getCategory()
    }

    override fun initActivity() {
        supportActionBar!!.hide()

        productAdapter = ProductAdapter(this, arrayListOf())

        categoryAdapter =
            CategoryAdapter(this, arrayListOf()) { category: DataCategory, position: Int ->
                kd_kategori = category.kd_kategori!!
                presenter.getProduct(category.kd_kategori!!)
            }
    }

    override fun initListener() {
        rcvCategory.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }
        rcvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }
        swipe.setOnRefreshListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> presenter.getCategory()
                rcvProduct.visibility -> presenter.getProduct(kd_kategori)
            }
        }

        imvClose.setOnClickListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> finish()
                rcvProduct.visibility -> {
                    rcvProduct.visibility = View.GONE
                    rcvCategory.visibility = View.VISIBLE
                    txvCategory.text = "Pilih Kategori"
                }
            }
        }

    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                swipe.isRefreshing = true
                rcvCategory.visibility = View.GONE
                rcvProduct.visibility = View.GONE
            }
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultCategory(responseCategoryList: ResponseCategoryList) {
        rcvCategory.visibility = View.VISIBLE
        val dataCategory: List<DataCategory> = responseCategoryList.data
        categoryAdapter.setData(dataCategory)
        txvCategory.text = "Pilih Kategori"
    }

    override fun onResultProduct(responseProductList: ResponseProductList) {
        rcvProduct.visibility = View.VISIBLE
        val dataProduct: List<DataProduct> = responseProductList.data
        productAdapter.setData(dataProduct)
        txvCategory.text = dataProduct[0].kategori
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when (View.VISIBLE) {
            rcvCategory.visibility -> finish()
            rcvProduct.visibility -> {
                rcvProduct.visibility = View.GONE
                rcvCategory.visibility = View.VISIBLE
                txvCategory.text = "Pilih Kategori"
            }
        }
    }

}