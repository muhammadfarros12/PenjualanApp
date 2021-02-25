package com.farroos.penjualanapp.data.model.product

import com.farroos.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukPresenter(val view: ProductContract.View) : ProductContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCategory() {
        view.onLoading(true)
        ApiService.endpoint.getCategory()
            .enqueue(object : Callback<ResponseCategoryList> {
                override fun onResponse(
                    call: Call<ResponseCategoryList>,
                    response: Response<ResponseCategoryList>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseCategoryList: ResponseCategoryList? = response.body()
                        responseCategoryList?.let {
                            view.onResultCategory(it)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseCategoryList>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }

    override fun getProduct(kd_kategori: Long) {
        view.onLoading(true)
        ApiService.endpoint.getProductByCategory(kd_kategori)
            .enqueue(object : Callback<ResponseProductList> {
                override fun onResponse(
                    call: Call<ResponseProductList>,
                    response: Response<ResponseProductList>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseProductList: ResponseProductList? = response.body()
                        responseProductList?.let {
                            view.onResultProduct(it)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseProductList>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }
}