package se.jun.allcommunity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.jsoup.select.Elements
import org.koin.java.KoinJavaComponent.inject
import se.jun.allcommunity.entity.ContentData
import se.jun.allcommunity.repository.ParsingRepository
import se.jun.allcommunity.utils.ListLiveData
import timber.log.Timber
import java.lang.Exception

class ParsingViewModel : ViewModel() {
    val parsingRepository: ParsingRepository by inject(ParsingRepository::class.java)
    private val _isProcessing = MutableLiveData<Boolean>()
    val isProcessing: LiveData<Boolean> = _isProcessing

    private val _ygosuData = ListLiveData<ContentData>()
    val ygosuData: LiveData<ArrayList<ContentData>> = _ygosuData

    private var parseYgosuDataJob: Job? = null

    fun parseYgosuData(page: Int = 1) {
        parseYgosuDataJob?.cancel()
        parseYgosuDataJob = viewModelScope.launch {
            delay(400L)
            _isProcessing.value = true


            try {
                val data =
                    parsingRepository.parseData("https://m.ygosu.com/board/real_article/?searcht=&search=&page=$page")
                        .await()
                val tmpList = ArrayList<ContentData>()


                val tmp: Elements = data.select("#article_list li a")

                for (x in tmp) {
                    val _href = "https://m.ygosu.com" + x.attr("href")
                    val rawText = x.text()
                    val subRawText = rawText.substringAfter("]").trim()


                    val splitText = subRawText.split("|")


                    val _count = splitText.get(2).substringAfter(": ").trim()


                    val _time = splitText.get(1).trimMargin()

                    val tmpTitle = splitText.get(0).substringBefore(" N").trim()
                    val titleSplit = tmpTitle.split(" ")
                    val _title = titleSplit.subList(0, titleSplit.lastIndex).joinToString("")


                    val _tmp = ContentData(
                        href = _href,
                        title = _title,
                        count = _count,
                        time = _time,
                        category = "Ygosu"
                    )
                    tmpList.add(_tmp)
                }
                if (page == 1) {
                    _ygosuData.value = tmpList
                    Timber.d("tmpList : $tmpList")
                } else {
                    //관련 문제 https://www.charlezz.com/?p=989
                    _ygosuData.addAll(tmpList)
                    Timber.d("tmpList : $tmpList")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }
}