package com.example.shopeeapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    private val _toastLiveData = MutableLiveData<Any>()
    val toastLiveData get() = _toastLiveData
    override fun onCleared() {
        super.onCleared()
    }
    fun showToast(any: Any) {
        _toastLiveData.postValue(any)
    }
}

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
}

fun CoroutineScope.launchSafe(
    coroutineContext: CoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    val job = SupervisorJob() + coroutineContext
    launch(job + coroutineExceptionHandler, start, block)
}