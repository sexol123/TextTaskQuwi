package com.example.testtask.data.response

import com.example.testtask.data.response.AppInit

data class LoginResponse(
    val app_init: AppInit,
    val just_signup: Boolean,
    val token: String
)