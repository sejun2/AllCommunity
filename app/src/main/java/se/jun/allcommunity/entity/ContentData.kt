package se.jun.allcommunity.entity

import com.google.gson.annotations.SerializedName

data class ContentData(
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("count")
    val count: String?,
    @SerializedName("href")
    val href: String?

)
