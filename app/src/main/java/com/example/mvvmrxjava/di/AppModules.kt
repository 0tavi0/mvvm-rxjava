package com.example.mvvmrxjava.di

import com.example.mvvmrxjava.data.repository.PersonImpl
import com.example.mvvmrxjava.data.repository.PersonNetworkDataSource
import com.example.mvvmrxjava.view.PersonViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModules {

    val modules = module {
        single { PersonNetworkDataSource() }
        single { PersonImpl(get()) }

        viewModel { PersonViewModel(get()) }
    }
}