package se.jun.allcommunity.repository

import org.jsoup.Jsoup

class ParsingRepository {
    suspend fun parseData(url: String) {

        val doc = Jsoup.connect(url).get()
    }
}