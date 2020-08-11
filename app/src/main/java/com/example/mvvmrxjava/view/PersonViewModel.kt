package com.example.mvvmrxjava.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmrxjava.data.entity.Response
import com.example.mvvmrxjava.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class PersonViewModel(private val personRepository: PersonRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val person: LiveData<Response> by lazy {
        personRepository.fetchSinglePerson(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        personRepository.networkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}