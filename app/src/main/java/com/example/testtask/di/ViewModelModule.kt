package com.example.testtask.di

import com.example.testtask.ui.content.ContentViewModel
import com.example.testtask.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
       viewModel {  LoginViewModel(get(), get()) }
       viewModel { ContentViewModel(get()) }
}