package se.jun.allcommunity.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import se.jun.allcommunity.entity.ContentData

class ParsingRepository {
    //데이터 파싱 모델
    fun parseData(url: String): Deferred<Document> {
        return CoroutineScope(Dispatchers.IO).async {
            Jsoup.connect(url).get()
        }
    }
}