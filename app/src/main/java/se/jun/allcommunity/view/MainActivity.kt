package se.jun.allcommunity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import se.jun.allcommunity.R
import se.jun.allcommunity.databinding.ActivityMainBinding
import se.jun.allcommunity.extension.toInvisible
import se.jun.allcommunity.extension.toToast
import se.jun.allcommunity.extension.toVisible
import se.jun.allcommunity.viewmodel.ParsingViewModel
import timber.log.Timber
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val parsingViewModel: ParsingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        initViewModel()

        parsingViewModel.parseData("https://www.naver.com")
    }

    private fun initView() {
        mBinding.tabRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun initViewModel() {
        parsingViewModel.isProcessing.observe(this) { isProcessing ->
            Timber.d("isProcessing : $isProcessing")
            if (isProcessing) mBinding.progressBar.toVisible()
            else mBinding.progressBar.toInvisible()
        }
        parsingViewModel.parsedData.observe(this) { data ->
            toToast(data)
        }
    }

}