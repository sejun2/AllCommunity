package se.jun.allcommunity.utils

import android.view.View

class OnThrottleClickListener(
    private val interval: Long,
    private val onClickListener: View.OnClickListener
) : View.OnClickListener {

    private var clickable = true
    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            v?.run {
                postDelayed({
                    clickable = true
                }, interval)
                onClickListener.onClick(v)
            }
        }
    }
}