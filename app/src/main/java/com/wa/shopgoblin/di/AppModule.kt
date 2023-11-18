package com.wa.shopgoblin.di

import com.wa.shopgoblin.data.UserRepository
import com.wa.shopgoblin.data.UserRepositoryImpl
import com.wa.shopgoblin.domain.UserStateHolder
import com.wa.shopgoblin.ui.main.UserViewModel
import com.wa.shopgoblin.ui.main.home.PlantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<UserRepository> { UserRepositoryImpl() }

    viewModel { UserViewModel(userDao = get()) }
    viewModel { PlantViewModel(plantDao = get()) }

    factory { UserStateHolder(repository = get()) }
}