package se.jun.allcommunity.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import se.jun.allcommunity.R
import se.jun.allcommunity.adapter.MainContentRecyclerViewAdapter
import se.jun.allcommunity.adapter.TabRecyclerViewAdapter
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.databinding.ActivityMainBinding
import se.jun.allcommunity.extension.toInvisible
import se.jun.allcommunity.extension.toVisible
import se.jun.allcommunity.view.fragment.MainContentFragment
import se.jun.allcommunity.viewmodel.DatabaseViewModel
import se.jun.allcommunity.viewmodel.ParsingViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val parsingViewModel: ParsingViewModel by viewModel() // similar with by viewModels()
    private val siteCategoryDao: SiteCategoryDao by inject()
    private val databaseViewModel: DatabaseViewModel by inject()

    private lateinit var tabRecyclerViewAdapter: TabRecyclerViewAdapter
    private lateinit var mainContentRecyclerViewAdapter: MainContentRecyclerViewAdapter

    private val categoryList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initCategory()
        initView()
        initViewModel()

        databaseViewModel.getCategoryData()
    }


    private fun initView() {
        setSupportActionBar(mBinding.toolbar)

        mBinding.tabRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        tabRecyclerViewAdapter = TabRecyclerViewAdapter(this.applicationContext)
        mBinding.tabRecyclerView.adapter = tabRecyclerViewAdapter

        //MainContentFragment
        supportFragmentManager.beginTransaction()
            .add(R.id.main_content_container, MainContentFragment.getInstance()).commit()

    }

    private fun initViewModel() {
        //ViewModel??? isProcessing LiveData Observing
        parsingViewModel.isProcessing.observe(this) { isProcessing ->
            Timber.d("isProcessing : $isProcessing")
            if (isProcessing) mBinding.progressBar.toVisible()
            else mBinding.progressBar.toInvisible()
        }


        databaseViewModel.categoryData.observe(this) {
            tabRecyclerViewAdapter.setTabData(it)
            tabRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun initCategory() {
        val category = resources.getStringArray(R.array.categories)
        categoryList.addAll(category)
        Timber.d("categoryList : $categoryList")
    }

}