package com.farroos.penjualanapp.ui.agent.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.agen.DataTransaction
import com.farroos.penjualanapp.data.model.agen.ResponseDataAgent
import kotlinx.android.synthetic.main.activity_agent_search.*

class AgentSearchActivity : AppCompatActivity(), AgentSearchContract.View {

    private lateinit var agentSearchPresenter: AgentSearchPresenter
    private lateinit var agentSearchAdapter: AgentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)

        agentSearchPresenter = AgentSearchPresenter(this)
        agentSearchPresenter.getAgent()

    }

    override fun initActivity() {
        supportActionBar!!.title = "Pilih Agent"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        agentSearchAdapter = AgentSearchAdapter(
            this,
            arrayListOf()
        ) { dataTransaction, position: Int ->
            com.farroos.penjualanapp.data.Constant.AGENT_ID = dataTransaction.kd_agen!!
            com.farroos.penjualanapp.data.Constant.AGENT_NAME = dataTransaction.nama_toko!!
            com.farroos.penjualanapp.data.Constant.IS_CHANGED = true
            finish()
        }

        edt_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                agentSearchPresenter.searchAgent(edt_search.text.toString())
                true
            } else {
                false
            }
        }

        rcv_agent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentSearchAdapter
        }

        swipe.setOnRefreshListener {
            agentSearchPresenter.getAgent()
        }

    }

    override fun onLoadingAgent(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgent(responseDataAgent: ResponseDataAgent) {
        val dataAgent: List<DataTransaction> = responseDataAgent.dataAgent
        agentSearchAdapter.setData(dataAgent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}