package com.example.testtask.di

import com.example.testtask.data.QuwiApi
import org.koin.dsl.module
import retrofit2.Retrofit

val networkApiModule = module {
    factory { provideUserApi(get()) }
}

fun provideUserApi(retrofit: Retrofit): QuwiApi = retrofit.create(QuwiApi::class.java)
