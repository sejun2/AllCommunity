package se.jun.allcommunity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.database.entity.SiteCategory

class DatabaseViewModel(val siteCategoryDao: SiteCategoryDao) : ViewModel() {
    private val _categoryData = MutableLiveData<List<SiteCategory>>()
    val categoryData: LiveData<List<SiteCategory>>
        get() = _categoryData

    private var getCategoryDataJob: Job? = null
    fun getCategoryData() {
        getCategoryDataJob?.cancel()

        getCategoryDataJob = viewModelScope.launch {
            delay(400L)
            withContext(Dispatchers.IO) {
                val tmp = siteCategoryDao.getAll()
                withContext(Dispatchers.Main) {
                    _categoryData.value = tmp
                }
            }
        }
    }
}