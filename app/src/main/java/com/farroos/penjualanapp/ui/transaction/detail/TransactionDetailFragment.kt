package com.farroos.penjualanapp.ui.transaction.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.ResponseDetailTransaction
import kotlinx.android.synthetic.main.fragment_transaction_detail.*

class TransactionDetailFragment : Fragment(), TransactionDetailContract.View {

    lateinit var detailAdapter: TransactionDetailAdapter
    lateinit var presenter: TransactionDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        presenter = TransactionDetailPresenter(this)

        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Detail Transaksi"
        presenter.getTransactionByInvoice(com.farroos.penjualanapp.data.Constant.INVOICE)
    }

    override fun initFragment() {
        detailAdapter = TransactionDetailAdapter(requireContext(), arrayListOf())
    }

    override fun initListener(view: View) {
        val txt_invoice = view.findViewById<TextView>(R.id.txt_detail_invoice)
        val rcv_transaction = view.findViewById<RecyclerView>(R.id.rcv_detail_transaction)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        txt_invoice.text = com.farroos.penjualanapp.data.Constant.INVOICE

        rcv_transaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }
        swipe.setOnRefreshListener {
            presenter.getTransactionByInvoice(com.farroos.penjualanapp.data.Constant.INVOICE)
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseDetailTransaction: ResponseDetailTransaction) {
        // cara memanggil detail
        responseDetailTransaction.data?.let {
            detailAdapter.setData(it)
        }
    }

    override fun onMsg(msg: String) {
        if (msg.isNotEmpty()){
            Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
        }
    }

}
