package com.example.testtask.ui.login

import com.example.testtask.R
import com.example.testtask.data.QuwiApiRepository
import com.example.testtask.data.localstorage.LocalStorageRepository
import com.example.testtask.data.response.AppInit
import com.example.testtask.ui.base.BaseViewModel
import com.example.testtask.util.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val quwiApiRepository: QuwiApiRepository,
    private val localStorageRepository: LocalStorageRepository
): BaseViewModel() {

    fun login(email: String, pass: String, onLoggedIn: (AppInit) -> Unit){
        if (email.isBlank() or pass.isBlank()){
            showError(getString(R.string.cntbe_empty))
            return
        }

        runWithLoadingCoroutine(block = {
            quwiApiRepository.login(email, pass)
        }, onSuccess = {
            localStorageRepository.saveBearerToken(it.token)
            withContext(Dispatchers.Main) {
                showMessage(msg = "Hello ${it.app_init.user.name}")
                onLoggedIn(it.app_init)
            }
        })
    }
}