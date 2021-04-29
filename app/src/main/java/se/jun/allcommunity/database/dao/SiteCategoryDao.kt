package se.jun.allcommunity.database.dao

import androidx.room.*
import se.jun.allcommunity.database.entity.SiteCategory

@Dao
interface SiteCategoryDao {
    @Query("select * from sitecategory")
    fun getAll(): List<String>

    @Query("delete from sitecategory where name = :name")
    fun delete(name: String)

    @Delete
    fun deleteAll()

    @Insert
    fun insertCategory(vararg category: SiteCategory)
}
