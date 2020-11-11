package com.example.testtask.util

import androidx.annotation.StringRes
import com.example.testtask.App

fun getString(@StringRes strRes: Int): String {
    return App.appContext.getString(strRes)
}