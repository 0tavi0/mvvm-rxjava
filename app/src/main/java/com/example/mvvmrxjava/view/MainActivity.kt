package com.example.mvvmrxjava.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmrxjava.R
import com.example.mvvmrxjava.data.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PersonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


}
