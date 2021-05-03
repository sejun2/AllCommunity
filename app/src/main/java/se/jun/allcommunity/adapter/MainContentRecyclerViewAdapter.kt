package se.jun.allcommunity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.jun.allcommunity.R
import se.jun.allcommunity.entity.ContentData
import se.jun.allcommunity.utils.OnThrottleClickListener
import se.jun.allcommunity.view.activity.WebViewActivity
import timber.log.Timber

class MainContentRecyclerViewAdapter(val mContext: Context) :
    RecyclerView.Adapter<MainContentRecyclerViewAdapter.MainContentViewHolder>() {
    private val _contentData = ArrayList<ContentData>()

    val contentData: ArrayList<ContentData>
        get() = _contentData

    fun setContentData(data: List<ContentData>) {
        _contentData.clear()
        _contentData.addAll(data)
        notifyDataSetChanged()
    }

    fun addContentData(data: List<ContentData>) {
        _contentData.addAll(data)
        notifyDataSetChanged()
    }

    fun clearContentData() {
        _contentData.clear()
        notifyDataSetChanged()
    }

    inner class MainContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_text_view)
        val category = itemView.findViewById<TextView>(R.id.category_text_view)
        val time = itemView.findViewById<TextView>(R.id.time_text_view)
        val count = itemView.findViewById<TextView>(R.id.view_count_text_view)
        val container = itemView.findViewById<LinearLayout>(R.id.content_item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return MainContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainContentViewHolder, position: Int) {
        holder.title.text = _contentData[position].title
        holder.time.text = _contentData[position].time
        holder.count.text = _contentData[position].count
        holder.category.text = _contentData[position].category

        holder.container.setOnClickListener(OnThrottleClickListener(1000L) {
            Timber.d("container clicked! href : ${_contentData.get(position).href} ")
            val webViewActivity = WebViewActivity()
            val intent = Intent(mContext, webViewActivity::class.java).putExtra(
                "url",
                _contentData.get(position).href
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        })
    }
    override fun getItemCount() = _contentData.size
}