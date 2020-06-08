package ua.gvv.paleo.util

import android.content.Context
import android.net.ConnectivityManager
import retrofit2.Response
import ua.gvv.paleo.data.remote.api.NetworkResponse
import ua.gvv.paleo.data.remote.api.Success
import ua.gvv.paleo.data.remote.api.Error

fun <T : Any> Response<T>.toNetworkResponse(): NetworkResponse<T> {
    return try {
        val response = this
        if (response.isSuccessful && response.body() != null) {
            Success(response.body()!!)
        } else {
            Error("${response.code()} ${response.message()}")
        }
    } catch (e: Exception) {
        Error(e.message)
    }
}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}