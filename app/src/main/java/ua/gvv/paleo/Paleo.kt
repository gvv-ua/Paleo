package ua.gvv.paleo

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import ua.gvv.paleo.di.paleoModules

class Paleo : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Paleo)
            modules(paleoModules)
        }

        initTimber()
        initCoil()
    }

    private fun initCoil() {
        val imageLoader = ImageLoader.Builder(this@Paleo)
            .componentRegistry {
                add(SvgDecoder(this@Paleo))
            }
            .build()
        Coil.setImageLoader(imageLoader)

    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}