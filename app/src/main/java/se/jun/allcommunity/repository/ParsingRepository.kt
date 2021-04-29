package se.jun.allcommunity.repository

import kotlinx.coroutines.*
import org.jsoup.Jsoup
import timber.log.Timber

class ParsingRepository {
    suspend fun parseData(url: String) {
        withContext(Dispatchers.IO) {
            val doc = Jsoup.connect(url).get()

            Timber.d("doc : ${doc.data()}")
        }
    }
}