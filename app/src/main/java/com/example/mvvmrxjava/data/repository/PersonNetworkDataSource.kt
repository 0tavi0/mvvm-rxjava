package com.example.mvvmrxjava.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmrxjava.data.api.ApiInterface
import com.example.mvvmrxjava.data.entity.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PersonNetworkDataSource(
    private val apiService: ApiInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState


    private val _responsePerson = MutableLiveData<Response>()
    val responsePerson: LiveData<Response> = _responsePerson

    fun getPerson() {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getPerson()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _responsePerson.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("PersonDataSource", it.message!!)
                        }
                    )
            )
        } catch (e: Exception) {
            Log.e("PersonDataSource", e.message!!)

        }
    }
}