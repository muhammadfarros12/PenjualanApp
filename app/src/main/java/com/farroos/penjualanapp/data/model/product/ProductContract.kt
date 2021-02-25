package com.farroos.penjualanapp.data.model.product

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(kd_kategori: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultCategory(responseCategoryList: ResponseCategoryList)
        fun onResultProduct(responseProductList: ResponseProductList)
    }

}