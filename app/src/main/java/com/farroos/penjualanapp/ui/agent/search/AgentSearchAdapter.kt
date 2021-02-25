package com.farroos.penjualanapp.ui.agent.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.agen.DataTransaction
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_agent_search.view.*
import kotlinx.android.synthetic.main.adapter_agent_search.view.img_rounded

class AgentSearchAdapter(
    val context: Context, var dataAgent: ArrayList<DataTransaction>,
    val clickListener: (DataTransaction, Int) -> Unit
) :
    RecyclerView.Adapter<AgentSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataAgent: DataTransaction) {
            view.txt_name_store.text = dataAgent.nama_toko
            view.txt_location.text = dataAgent.alamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent_search, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgent[position])
        GlideHelper.setImage(context, dataAgent[position].gambar_toko!!, holder.view.img_rounded)
        holder.view.crvAgent.setOnClickListener {
            com.farroos.penjualanapp.data.Constant.AGENT_ID = dataAgent[position].kd_agen!!
            clickListener(dataAgent[position], position)
        }
    }

    override fun getItemCount(): Int = dataAgent.size

    fun setData(newDataAgent: List<DataTransaction>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

}