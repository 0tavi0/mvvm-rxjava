package com.example.mvvmrxjava.view

import androidx.lifecycle.LiveData
import com.example.mvvmrxjava.data.api.ApiInterface
import com.example.mvvmrxjava.data.entity.Response
import com.example.mvvmrxjava.data.repository.NetworkState
import com.example.mvvmrxjava.data.repository.PersonNetworkDataSource
import io.reactivex.disposables.CompositeDisposable

class PersonRepository(private val apiService:ApiInterface) {

    lateinit var personNetworkDataSource: PersonNetworkDataSource

    fun fetchSinglePerson(compositeDisposable: CompositeDisposable):LiveData<Response>{
        personNetworkDataSource = PersonNetworkDataSource(apiService, compositeDisposable)
        personNetworkDataSource.getPerson()

        return personNetworkDataSource.responsePerson
    }

    fun networkState() : LiveData<NetworkState>{
        return personNetworkDataSource.networkState
    }
}