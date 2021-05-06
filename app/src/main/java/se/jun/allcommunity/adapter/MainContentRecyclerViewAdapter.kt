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
import se.jun.allcommunity.databinding.ContentItemBinding
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

    inner class MainContentViewHolder(private val mBinding : ContentItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(contentData : ContentData){
            mBinding.titleTextView.text = contentData.title
            mBinding.categoryTextView.text = contentData.category
            mBinding.timeTextView.text = contentData.time
            mBinding.viewCountTextView.text = contentData.count
            mBinding.contentItemContainer.setOnClickListener(OnThrottleClickListener(1000L){
                Timber.d("container clicked! href : ${contentData.href} ")
                val webViewActivity = WebViewActivity()
                val intent = Intent(mContext, webViewActivity::class.java).putExtra(
                    "url",
                    contentData.href
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            } )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainContentViewHolder {
        val binding = ContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainContentViewHolder, position: Int) {
        holder.bind(_contentData[position])
    }

    override fun getItemCount() = _contentData.size
}