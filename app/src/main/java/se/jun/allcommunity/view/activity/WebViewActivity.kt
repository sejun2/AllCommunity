package se.jun.allcommunity.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.jun.allcommunity.R
import se.jun.allcommunity.databinding.ActivityWebViewBinding

class WebViewActivity() : AppCompatActivity() {
    private lateinit var mBinding : ActivityWebViewBinding

    override fun onResume() {
        super.onResume()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        loadUrl(intent.getStringExtra("url").toString())
    }

    private fun loadUrl(url : String){
        mBinding.webView.loadUrl(url)
    }

}