package se.jun.allcommunity.koin_module

import androidx.room.DatabaseView
import org.koin.dsl.module
import se.jun.allcommunity.viewmodel.DatabaseViewModel
import se.jun.allcommunity.viewmodel.ParsingViewModel

val viewModelModule = module {
    factory { ParsingViewModel() }
    factory { DatabaseViewModel(get()) }
}