package se.jun.allcommunity.utils

import android.view.View

/*
Throttle 은 이벤트를 일정한 주기마다 발생하도록 하는 기술입니다.
예를 들어 Throttle 의 설정시간으로 1ms 를 주게되면 해당 이벤트는 1ms 동안 최대 한번만 발생하게 됩니다.

Throttle : 마지막 함수가 호출된 후 일정 시간이 지나기 전에 다시 호출되지 않도록 하는 것

출처: https://webclub.tistory.com/607 [Web Club]
 */
class OnThrottleClickListener(
    private val interval: Long = 300L,
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