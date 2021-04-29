package se.jun.allcommunity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import se.jun.allcommunity.repository.ParsingRepository
import java.lang.Exception

class ParsingViewModel : ViewModel() {
    val parsingRepository: ParsingRepository by inject(ParsingRepository::class.java)
    private val _isProcessing = MutableLiveData<Boolean>()
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    private val _parsedData = MutableLiveData<String>()
    val parsedData: LiveData<String>
        get() = _parsedData

    fun parseData(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _isProcessing.value = true
                parsingRepository.parseData(url)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }
}