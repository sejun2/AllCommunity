package se.jun.allcommunity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SiteCategory(
    @PrimaryKey(autoGenerate = true)
    val index: Int?,

    @ColumnInfo(name = "name")
    val name: String,
)
