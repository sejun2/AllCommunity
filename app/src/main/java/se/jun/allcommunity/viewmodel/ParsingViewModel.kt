package se.jun.allcommunity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.select.Elements
import org.koin.java.KoinJavaComponent.inject
import se.jun.allcommunity.entity.ContentData
import se.jun.allcommunity.repository.ParsingRepository
import timber.log.Timber
import java.lang.Exception

class ParsingViewModel : ViewModel() {
    val parsingRepository: ParsingRepository by inject(ParsingRepository::class.java)
    private val _isProcessing = MutableLiveData<Boolean>()
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    private val _ygosuData = MutableLiveData<ArrayList<ContentData>>()
    val ygosuData: LiveData<ArrayList<ContentData>>
        get() = _ygosuData

    fun parseYgosuData(page : Int = 1) {
        viewModelScope.launch {
            _isProcessing.value = true
            try {
                val data = parsingRepository.parseData("https://m.ygosu.com/board/real_article/?searcht=&search=&page=$page").await()
                val tmpList = ArrayList<ContentData>()


                val tmp : Elements = data.select("#article_list li a")

                for(x in tmp){
                    val _href = "https://m.ygosu.com"+ x.attr("href")
                    val rawText = x.text()
                    val subRawText = rawText.substringAfter("]").trim()


                    val splitText = subRawText.split("|")


                    val _count = splitText.get(2).substringAfter(": ").trim()


                    val _time = splitText.get(1).trimMargin()

                    val tmpTitle = splitText.get(0).substringBefore(" N").trim()
                    val titleSplit = tmpTitle.split(" ")
                    val _title = titleSplit.subList(0, titleSplit.lastIndex).joinToString("")


                    val _tmp = ContentData(href = _href, title = _title, count = _count, time = _time, category = "Ygosu")
                    tmpList.add(_tmp)
                }

                _ygosuData.value = tmpList
                Timber.d("tmpList : $tmpList")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }
}