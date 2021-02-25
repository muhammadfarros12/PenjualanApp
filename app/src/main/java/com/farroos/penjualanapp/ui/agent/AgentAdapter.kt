package com.farroos.penjualanapp.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.agen.DataTransaction
import com.farroos.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_agent.view.*

class AgentAdapter (val context: Context, var dataAgent: ArrayList<DataTransaction>,
                    val clickListener: (DataTransaction, Int, String) -> Unit):
        RecyclerView.Adapter<AgentAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataAgent: DataTransaction) {
            view.txt_namaToko.text = dataAgent.nama_toko
            view.txt_lokasi.text = dataAgent.alamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgent[position])
        GlideHelper.setImage(context, dataAgent[position].gambar_toko!!, holder.view.img_rounded)
        holder.view.img_rounded.setOnClickListener {
            com.farroos.penjualanapp.data.Constant.AGENT_ID = dataAgent[position].kd_agen!!
            clickListener(dataAgent[position], position, "detail")
        }

        holder.view.txt_option.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.view.txt_option)
            popupMenu.inflate(R.menu.menu_option)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_update -> {
                        com.farroos.penjualanapp.data.Constant.AGENT_ID = dataAgent[position].kd_agen!!
                        clickListener(dataAgent[position], position, "update")
                    }
                    R.id.action_delete -> {
                        com.farroos.penjualanapp.data.Constant.AGENT_ID = dataAgent[position].kd_agen!!
                        clickListener(dataAgent[position], position, "delete")
                    }
                }

                true

            }

            popupMenu.show()

        }
    }

    override fun getItemCount(): Int = dataAgent.size

    fun setData(newDataAgent: List<DataTransaction>){
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

    fun removeAgent(position: Int){
        dataAgent.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAgent.size)
    }

}