package com.example.testtask.ui.base

sealed class State {
    object NONE: State()
    object SUCCESS: State()
    data class ERROR(val msg: String): State()
    object LOADING: State()
}