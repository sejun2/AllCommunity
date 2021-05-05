package se.jun.allcommunity.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView

import androidx.recyclerview.widget.RecyclerView
import se.jun.allcommunity.R
import se.jun.allcommunity.database.entity.SiteCategory
import se.jun.allcommunity.utils.OnThrottleClickListener
import se.jun.allcommunity.view.fragment.MainContentFragment
import timber.log.Timber


class TabRecyclerViewAdapter(val mContext: Context) :
    RecyclerView.Adapter<TabRecyclerViewAdapter.TabViewHolder>() {
    val tabData: ArrayList<SiteCategory>
        get() = _tabData

    private val _tabData = ArrayList<SiteCategory>()

    private var selectedPosition = 0

    fun setTabData(data: List<SiteCategory>) {
        _tabData.clear()
        _tabData.addAll(data)
    }

    fun clearTabData() {
        _tabData.clear()
        notifyDataSetChanged()
    }

    fun addTabData(item: SiteCategory) {
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
        holder.site_name.text = _tabData[position].name

        holder.site_name.setOnClickListener(OnThrottleClickListener(1000L) {
            Timber.d("site_name onClicked!")

            MainContentFragment.getInstance().currentCategory = _tabData[position]
            MainContentFragment.getInstance().currentPage = _tabData[position].start

            MainContentFragment.getInstance()
                .parseWeb(_tabData[position].name, _tabData[position].url, _tabData[position].start)
        })

    }

    override fun getItemCount() = _tabData.size


}