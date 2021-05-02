package se.jun.allcommunity.koin_module

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import se.jun.allcommunity.database.CategoryDatabase
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.repository.ParsingRepository

val ioModule = module {
    single { ParsingRepository() }

    single { Room.databaseBuilder(androidContext(), CategoryDatabase::class.java, "category_db").build() }

    single { provideSiteCategoryDao(get()) }
}

internal fun provideSiteCategoryDao(db : CategoryDatabase): SiteCategoryDao {
    return db.siteCategoryDao()
}
