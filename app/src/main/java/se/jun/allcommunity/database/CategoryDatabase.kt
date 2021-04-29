package se.jun.allcommunity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import se.jun.allcommunity.database.entity.SiteCategory

@Database(entities = [SiteCategory::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

}