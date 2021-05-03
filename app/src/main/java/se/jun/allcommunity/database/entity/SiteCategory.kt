package se.jun.allcommunity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SiteCategory(
    @ColumnInfo(name = "index")
    val index: Int?,

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
)
