package ua.gvv.paleo.data.remote.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ua.gvv.paleo.BuildConfig
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    private const val OK_HTTP_TIMEOUT = 30L
    const val PALEO_API_URL = "https://paleobiodb.org/data1.2/"
    const val PHYLOPIC_API_URL = "http://phylopic.org/api/a/"

    internal inline fun <reified T> createService(
        context: Context?,
        moshi: Moshi,
        apiUrl: String
    ): T = with(OkHttpClient.Builder()) {

        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            context?.let { addInterceptor(ChuckerInterceptor(it)) }
        }

        connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(build())
            .build()
            .create(T::class.java)
    }
}
