package ua.gvv.paleo.util

import kotlinx.coroutines.CoroutineDispatcher


interface DispatchersProvider {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}