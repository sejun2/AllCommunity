package se.jun.allcommunity.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.database.entity.SiteCategory

@Database(entities = [SiteCategory::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun siteCategoryDao() : SiteCategoryDao
}