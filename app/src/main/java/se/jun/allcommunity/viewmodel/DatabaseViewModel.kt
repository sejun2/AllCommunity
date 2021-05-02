package se.jun.allcommunity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.experimental.property.inject
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.database.entity.SiteCategory

class DatabaseViewModel(val siteCategoryDao: SiteCategoryDao) : ViewModel() {
    private val _categoryData  = MutableLiveData<List<SiteCategory>>()
    val categoryData : LiveData<List<SiteCategory>>
    get() = _categoryData

    fun getCategoryData(){
        CoroutineScope(Dispatchers.IO).launch {
            val tmp = siteCategoryDao.getAll()
            withContext(Dispatchers.Main){
                _categoryData.value  = tmp
            }
        }

    }
}