package se.jun.allcommunity.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import se.jun.allcommunity.adapter.MainContentRecyclerViewAdapter
import se.jun.allcommunity.databinding.FragmentMainContentBinding
import se.jun.allcommunity.view.activity.MainActivity
import se.jun.allcommunity.viewmodel.ParsingViewModel
import timber.log.Timber

class MainContentFragment : Fragment() {
    private lateinit var mBinding: FragmentMainContentBinding
    private lateinit var mainContentRecyclerViewAdapter: MainContentRecyclerViewAdapter
    private val parsingViewModel: ParsingViewModel by sharedViewModel()

    private var currentPage = 1

    companion object {
        private var INSTANCE: MainContentFragment? = null

        @JvmStatic
        fun getInstance(): MainContentFragment {
            if (INSTANCE == null) {
                INSTANCE = MainContentFragment()
            }
            return INSTANCE!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinding = FragmentMainContentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()

        parsingViewModel.parseYgosuData(1)

    }

    private fun initView() {
        mainContentRecyclerViewAdapter =
            MainContentRecyclerViewAdapter(requireActivity().applicationContext)
        mBinding.mainContentRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        mBinding.mainContentRecyclerView.adapter = mainContentRecyclerViewAdapter

        mBinding.mainContentRecyclerView.setOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!mBinding.mainContentRecyclerView.canScrollVertically(1)){
                    //end of the list
                    Timber.d("this is end of the list")
                    parsingViewModel.parseYgosuData(++currentPage)
                }
            }
        })

        mBinding.swipeLayout.setOnRefreshListener {
            mBinding.swipeLayout.isRefreshing = false
            currentPage = 1
            parsingViewModel.parseYgosuData(currentPage)
        }


    }

    private fun initViewModel() {
        parsingViewModel.ygosuData.observe(requireActivity()) {
            Timber.d("ygosuData observed!")
            mainContentRecyclerViewAdapter.setContentData(it)
            mainContentRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    fun clearFragment(){
        currentPage = 1
    }

    fun parseYgosu() = parsingViewModel.parseYgosuData(currentPage)
    fun parseClien(){

    }
    fun parse82Cook(){

    }
    fun parsePomPu(){

    }
    fun parseHumorUniv(){

    }
    fun parseDogDrip(){

    }
    fun parseDdanzi(){

    }
    fun parseBobae(){

    }
    fun parseMlbPark(){

    }
    fun parseGasengi(){

    }
    fun parseFmKorea(){

    }
}