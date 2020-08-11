package com.example.mvvmrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmrxjava.data.api.ApiClient
import com.example.mvvmrxjava.data.api.ApiInterface
import com.example.mvvmrxjava.data.repository.NetworkState
import com.example.mvvmrxjava.view.PersonRepository
import com.example.mvvmrxjava.view.PersonViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : PersonViewModel
    private lateinit var personRepository: PersonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : ApiInterface = ApiClient.getClient()
        personRepository = PersonRepository(apiService)

        viewModel = getViewModel()

        viewModel.person.observe(this, Observer {
            Log.e("Activity", "${it.results}")
            tv_test.text = it.results[0].email
        })

        viewModel.networkState.observe(this, Observer {
            progress.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            tv_text_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })



    }

    private fun getViewModel(): PersonViewModel{
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(personRepository) as T
            }
        }) [PersonViewModel::class.java]
    }
}
