package se.jun.allcommunity.koin_module

import org.koin.core.module.Module
import org.koin.dsl.module
import se.jun.allcommunity.repository.ParsingRepository

val ioModule = module {
    single { ParsingRepository() }
}