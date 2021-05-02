package se.jun.allcommunity

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.BuildConfig
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import se.jun.allcommunity.database.dao.SiteCategoryDao
import se.jun.allcommunity.database.entity.SiteCategory
import se.jun.allcommunity.koin_module.ioModule
import se.jun.allcommunity.koin_module.viewModelModule
import timber.log.Timber

class App : Application() {
    val siteCategoryDao: SiteCategoryDao by inject()

    override fun onCreate() {
        super.onCreate()

        if (se.jun.allcommunity.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)

            modules(listOf(ioModule, viewModelModule))
        }
        CoroutineScope(Dispatchers.IO).launch {
            initCategory()
        }


    }
    private fun initCategory(){
        Timber.d("initCategory ...")
        if(!getSharedPreferences("category", MODE_PRIVATE).getBoolean("isCategoryInit", false)) {
            val res = resources.getStringArray(R.array.categories).toList()

            for(_res in res) {
                val tmpRes = SiteCategory(null, _res)
                siteCategoryDao.insertCategory(tmpRes)
            }
            getSharedPreferences("category", MODE_PRIVATE).edit().putBoolean("isCategoryInit", true).commit()
        }
    }
}