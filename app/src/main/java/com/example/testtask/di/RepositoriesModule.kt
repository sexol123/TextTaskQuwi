package com.example.testtask.di

import com.example.testtask.data.QuwiApiRepository
import com.example.testtask.data.localstorage.LocalStorageRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory { QuwiApiRepository(get()) }
    factory { LocalStorageRepository(get()) }
}