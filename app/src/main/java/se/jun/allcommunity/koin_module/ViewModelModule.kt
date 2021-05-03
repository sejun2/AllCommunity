package se.jun.allcommunity.koin_module

import androidx.room.DatabaseView
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import se.jun.allcommunity.viewmodel.DatabaseViewModel
import se.jun.allcommunity.viewmodel.ParsingViewModel

val viewModelModule = module {
    viewModel { ParsingViewModel() }
    viewModel { DatabaseViewModel(get()) }
}