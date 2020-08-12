package com.example.mvvmrxjava.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmrxjava.data.models.Response
import com.example.mvvmrxjava.data.repository.NetworkState
import com.example.mvvmrxjava.data.repository.PersonImpl
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class PersonViewModel(private val personImpl: PersonImpl) : ViewModel() {
    private val _person = MutableLiveData<Response>()
    val person: LiveData<Response> = _person
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    fun getPersonRefactor() {
        personImpl.getPerson()
            .subscribe(object : SingleObserver<Response> {
                override fun onSuccess(t: Response) {
                    _networkState.postValue(NetworkState.LOADED)
                    _person.postValue(t)
                }
                override fun onSubscribe(d: Disposable) {
                    _networkState.postValue(NetworkState.LOADING)
                }
                override fun onError(e: Throwable) {
                    _networkState.postValue(NetworkState.ERROR)
                }
            })
    }
}