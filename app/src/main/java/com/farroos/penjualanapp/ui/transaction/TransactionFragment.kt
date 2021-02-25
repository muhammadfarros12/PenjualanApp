package com.farroos.penjualanapp.ui.transaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.database.PrefsManager
import com.farroos.penjualanapp.data.model.transaction.DataTransaction
import com.farroos.penjualanapp.data.model.transaction.ResponseTransactionList
import com.farroos.penjualanapp.ui.cart.CartActivity
import com.farroos.penjualanapp.ui.transaction.detail.TransactionDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_transaction.*


class TransactionFragment : Fragment(), TransactionContract.View {

    lateinit var prefsManajer: PrefsManager
    lateinit var transactionAdapter: TransactionAdapter
    lateinit var presenter: TransactionPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transaction, container, false)

        prefsManajer = PrefsManager(requireContext())
        presenter = TransactionPresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername(prefsManajer.prefsUsername)
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(requireContext(), arrayListOf()) {
            dataTransaction, position ->
            onClickTransaction(dataTransaction.no_faktur!!)
        }
    }

    override fun initListener(view: View) {
        val rcv_transaction = view.findViewById<RecyclerView>(R.id.rcv_transaction)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        rcv_transaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(
                prefsManajer.prefsUsername
            )
        }

        fab.setOnClickListener {
            requireContext().startActivity(Intent(context, CartActivity::class.java))
        }

    }

    override fun onLoading(loading: Boolean) {
        when (loading){
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
        val dataTransaction: List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {
        com.farroos.penjualanapp.data.Constant.INVOICE = invoice
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, TransactionDetailFragment(), "tag_detail")
            .commit()
    }

}