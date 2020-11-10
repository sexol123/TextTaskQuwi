package com.example.testtask.data.localstorage

import android.content.Context
import android.content.SharedPreferences

class LocalStorageRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(LOCAL_STORAGE_PREF, Context.MODE_PRIVATE)

    fun getBearerToken(): BearerToken {
        return BearerToken(sharedPreferences.getString(BEARER, null))
    }

    fun saveBearerToken(bearer: String) {
        sharedPreferences.edit().putString(BEARER, bearer).apply()
    }

    fun clearStorage() = sharedPreferences.edit().clear()

    companion object {
        private const val LOCAL_STORAGE_PREF = "LOCAL_STORAGE_PRF"
        private const val BEARER = "BEARER"
    }
}

data class BearerToken(
    val value: String?
) {
    companion object {
        val empty = BearerToken(null)
    }
}