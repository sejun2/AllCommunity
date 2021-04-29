package se.jun.allcommunity.koin_module

import org.koin.dsl.module
import se.jun.allcommunity.viewmodel.ParsingViewModel

val viewModelModule = module {
    factory { ParsingViewModel() }
}