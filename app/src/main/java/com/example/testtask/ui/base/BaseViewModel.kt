package com.example.testtask.ui.base

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    protected val _loadingStateData = MutableLiveData<State>(State.NONE)

    val loadingStateData: LiveData<State> = _loadingStateData

    init {
        kotlin.runCatching {  }
    }

    public fun <R> runWithLoading(
        block: () -> R,
        onSuccess: (R) -> Unit = {}, onError: (Throwable) -> Unit = {}
    ) {
        _loadingStateData.value = State.LOADING

        kotlin.runCatching {
            block()
        }.onFailure {
            _loadingStateData.value = (State.ERROR(it.localizedMessage ?: it.message ?: "Ooops"))
            onError(it)
            _loadingStateData.value =(State.NONE)
        }.onSuccess {
            _loadingStateData.value =(State.SUCCESS)
            onSuccess(it)
            _loadingStateData.value =(State.NONE)
        }
    }

    public  fun <R> runWithLoadingCoroutine(block: suspend () -> R, onSuccess: suspend (R) -> Unit = {}, onError: suspend (Throwable) -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingStateData.postValue(State.LOADING)

            kotlin.runCatching {
                block()
            }.onFailure {
                _loadingStateData.postValue(State.ERROR(it.localizedMessage ?: it.message ?: "Ooops"))
                 onError(it)
                _loadingStateData.postValue(State.NONE)
            }.onSuccess {
                _loadingStateData.postValue(State.SUCCESS)
                onSuccess(it)
                _loadingStateData.postValue(State.NONE)
            }
        }
    }

    protected fun showError(msg: String){
        _loadingStateData.value = (State.ERROR(msg))
        _loadingStateData.value = (State.NONE)
    }

    protected fun showMessage(msg: String){
        Toast.makeText(App.appContext, msg, Toast.LENGTH_SHORT).show()
    }
}
