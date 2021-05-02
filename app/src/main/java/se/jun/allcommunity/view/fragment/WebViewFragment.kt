package se.jun.allcommunity.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.jun.allcommunity.R
import se.jun.allcommunity.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    private lateinit var mBinding : FragmentWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinding = FragmentWebViewBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    fun loadUrl(url : String){
        mBinding.webView.loadUrl(url)
    }
    companion object {
        private var INSTANCE: WebViewFragment? = null

        @JvmStatic
        fun getInstance(): WebViewFragment {
            if (INSTANCE == null) {
                INSTANCE = WebViewFragment()
            }
            return INSTANCE!!
        }
    }
}