package com.example.mvvmrxjava.data.repository

import com.example.mvvmrxjava.data.api.ApiClient
import com.example.mvvmrxjava.data.models.Response
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PersonNetworkDataSource {

    fun getPersonRefactor(): Single<Response> {
        return ApiClient.getClient()
            .getPerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}