package se.jun.allcommunity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.jun.allcommunity.R

class TabRecyclerViewAdapter : RecyclerView.Adapter<TabRecyclerViewAdapter.TabViewHolder>() {
    val tabData: ArrayList<String>
        get() = _tabData

    private val _tabData = ArrayList<String>()

    fun setTabData(data: List<String>) {
        _tabData.clear()
        _tabData.addAll(data)
    }

    fun clearTabData() {
        _tabData.clear()
        notifyDataSetChanged()
    }

    fun addTabData(item: String) {
        _tabData.add(item)
        notifyDataSetChanged()
    }

    inner class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val site_name = itemView.findViewById<TextView>(R.id.site_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.site_name.text = _tabData[position]
    }

    override fun getItemCount() = _tabData.size
}