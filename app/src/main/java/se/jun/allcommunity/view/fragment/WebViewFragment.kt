package se.jun.allcommunity.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.jun.allcommunity.R

class WebViewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    companion object {
        private var INSTANCE: WebViewFragment? = null

        @JvmStatic
        fun getInstance(param1: String, param2: String): WebViewFragment {
            if (INSTANCE == null) {
                INSTANCE = WebViewFragment()
            }
            return INSTANCE!!
        }
    }
}