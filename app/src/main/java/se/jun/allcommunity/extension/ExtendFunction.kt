package se.jun.allcommunity.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import android.widget.Toast
import se.jun.allcommunity.R

/**
 * View Extend Functions
 */
fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun CheckedTextView.toChecked() {
    setTextColor(resources.getColor(R.color.white))
    background = resources.getDrawable(R.drawable.tab_item_selected)
    this.isChecked = true
}

fun CheckedTextView.toUnChecked() {
    setTextColor(resources.getColor(android.R.color.holo_red_light))
    background = resources.getDrawable(R.drawable.tab_item_unselected)
    this.isChecked = false
}

/**
 * Context Extend Functions
 */
fun Context.toToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

//Checks if internet is available or not
fun Context.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

/**
 * Activity Extend Functions
 */
//Returns screen width
fun Activity.screenWidth(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

//Returns screen height
fun Activity.screenHeight(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}