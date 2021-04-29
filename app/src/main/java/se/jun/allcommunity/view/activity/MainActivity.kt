package se.jun.allcommunity.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import se.jun.allcommunity.R
import se.jun.allcommunity.adapter.TabRecyclerViewAdapter
import se.jun.allcommunity.databinding.ActivityMainBinding
import se.jun.allcommunity.extension.toInvisible
import se.jun.allcommunity.extension.toToast
import se.jun.allcommunity.extension.toVisible
import se.jun.allcommunity.viewmodel.ParsingViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val parsingViewModel: ParsingViewModel by viewModel()

    private lateinit var tabRecyclerViewAdapter: TabRecyclerViewAdapter

    private val categoryList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initCategory()
        initView()
        initViewModel()

        //for test
        parsingViewModel.parseData("https://www.naver.com")

    }

    private fun initView() {
        mBinding.tabRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        tabRecyclerViewAdapter = TabRecyclerViewAdapter()
        mBinding.tabRecyclerView.adapter = tabRecyclerViewAdapter

        tabRecyclerViewAdapter.setTabData(categoryList)
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

    private fun initCategory() {
        val category = resources.getStringArray(R.array.categories)
        categoryList.addAll(category)
        Timber.d("categoryList : $categoryList")
    }

}