package se.jun.allcommunity.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView

import androidx.recyclerview.widget.RecyclerView
import se.jun.allcommunity.R
import se.jun.allcommunity.extension.toChecked
import se.jun.allcommunity.extension.toUnChecked
import se.jun.allcommunity.utils.OnThrottleClickListener
import timber.log.Timber


class TabRecyclerViewAdapter(val mContext: Context) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.TabViewHolder>() {
    val tabData: ArrayList<String>
        get() = _tabData

    private val _tabData = ArrayList<String>()

    private var selectedPosition = 0

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
        val site_name = itemView.findViewById<CheckedTextView>(R.id.site_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.site_name.text = _tabData[position]

        if (holder.site_name.isActivated) {
            (holder.site_name as CheckedTextView).toChecked()
        } else {
            (holder.site_name as CheckedTextView).toUnChecked()
        }

        holder.site_name.setOnClickListener(OnThrottleClickListener(1000L){
            Timber.d("site_name onClicked!")
        })

    }

    override fun getItemCount() = _tabData.size


}