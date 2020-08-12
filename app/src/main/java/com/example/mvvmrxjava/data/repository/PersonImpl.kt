package com.example.mvvmrxjava.data.repository

import com.example.mvvmrxjava.data.models.Response
import io.reactivex.Single

class PersonImpl(private val personNetworkDataSource: PersonNetworkDataSource) : PersonRepository {
    override fun getPerson(): Single<Response> {
        return personNetworkDataSource.getPersonRefactor()
    }
}