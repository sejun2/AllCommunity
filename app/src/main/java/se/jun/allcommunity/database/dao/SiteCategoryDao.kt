package se.jun.allcommunity.database.dao

import androidx.room.*
import se.jun.allcommunity.database.entity.SiteCategory

@Dao
interface SiteCategoryDao {
    @Query("select * from sitecategory")
    fun getAll(): List<SiteCategory>

    @Query("delete from sitecategory where name = :name")
    fun delete(name: String)

    @Query("delete from sitecategory")
    fun deleteAll()

    @Insert
    fun insertCategory(vararg category: SiteCategory)
}
