package com.example.mvvmrxjava.data.api

import com.example.mvvmrxjava.data.entity.Response
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/")
    fun getPerson() : Single<Response>
}