package ua.gvv.paleo.data.remote.api


sealed class NetworkResponse<out T : Any>

class Success<T : Any>(val data: T) : NetworkResponse<T>()
class Error(val errorMessage: String?) : NetworkResponse<Nothing>()

