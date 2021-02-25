package com.farroos.penjualanapp.ui.transaction.detail

import com.farroos.penjualanapp.data.model.ResponseDetailTransaction
import com.farroos.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailPresenter (val view: TransactionDetailContract.View): TransactionDetailContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByInvoice(invoice: String) {
        view.onLoading(true)
        ApiService.endpoint.getTransactionByInvoice(invoice)
            .enqueue(object : Callback<ResponseDetailTransaction>{
                override fun onResponse(
                    call: Call<ResponseDetailTransaction>,
                    responseTransaction: Response<ResponseDetailTransaction>
                ) {
                    view.onLoading(false)
                    if (responseTransaction.isSuccessful){
                        val responseDetailTransaction: ResponseDetailTransaction? = responseTransaction.body()
                        /**pakai ini agar tidak terjadi null */
                        /**sebelumnya:
                        view.onResult(responseDetailTransaction!!)*/
                        responseDetailTransaction?.let {data ->
                            view.onResult(data)
                            view.onMsg("Sukses")
                        }
                        if(responseDetailTransaction == null){
                            view.onMsg("Data Tidak ditemukan")
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDetailTransaction>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }
}