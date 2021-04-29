package se.jun.allcommunity.repository

import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import timber.log.Timber

class ParsingRepository {
    fun parseData(url: String): Deferred<Document> {
        return CoroutineScope(Dispatchers.IO).async {
            Jsoup.connect(url).get()
        }
    }
}