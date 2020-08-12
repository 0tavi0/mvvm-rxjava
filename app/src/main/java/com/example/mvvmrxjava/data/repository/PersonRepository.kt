package com.example.mvvmrxjava.data.repository

import com.example.mvvmrxjava.data.models.Response
import io.reactivex.Single

interface PersonRepository {
    fun getPerson() : Single<Response>
}