package com.example.testtask.ui.login

import androidx.lifecycle.*
import com.example.testtask.data.QuwiApiRepository
import com.example.testtask.data.localstorage.LocalStorageRepository
import com.example.testtask.data.response.AppInit
import com.example.testtask.data.response.LoginResponse
import com.example.testtask.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val quwiApiRepository: QuwiApiRepository,
    private val localStorageRepository: LocalStorageRepository
): BaseViewModel() {

    private val _isAuthLiveData = object: MutableLiveData<Boolean>(){
        override fun onActive() {
            super.onActive()
            postValue(localStorageRepository.getBearerToken().value != null)
        }
    }
    val isAuthLiveData: LiveData<Boolean> = _isAuthLiveData

    fun login(email: String, pass: String, onLoggedIn: (AppInit) -> Unit){
        if (email.isBlank() or pass.isBlank()){
            showError("Fields can't be empty")
            return
        }

        runWithLoadingCoroutine(block = {
            quwiApiRepository.login(email, pass)
        }, onSuccess = {
            localStorageRepository.saveBearerToken(it.token)
            withContext(Dispatchers.Main){
                showMessage("Hello ${it.app_init.user.name}")
                onLoggedIn(it.app_init)
            }
        })
    }
}