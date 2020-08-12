package com.example.mvvmrxjava.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmrxjava.R
import com.example.mvvmrxjava.data.repository.NetworkState
import com.example.mvvmrxjava.data.repository.PersonImpl
import com.example.mvvmrxjava.data.repository.PersonNetworkDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonViewModel
    private lateinit var personImpl: PersonImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personNetworkDataSource = PersonNetworkDataSource()

        personImpl = PersonImpl(personNetworkDataSource)
        viewModel = getViewModel()
        viewModel.getPersonRefactor()

        viewModel.person.observe(this, Observer {
            Log.e("Activity", "${it.results}")
            tv_test.text = it.results[0].email
        })

        viewModel.networkState.observe(this, Observer {
            progress.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            tv_text_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    private fun getViewModel(): PersonViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(personImpl) as T
            }
        })[PersonViewModel::class.java]
    }
}
