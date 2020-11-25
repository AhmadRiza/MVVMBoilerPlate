package com.github.ahmadriza.mvvmboilerplate.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()

    }



    private fun initObservers(){

        viewModel.isLogin.observe(this){

            when(it.status){
                Resource.Status.SUCCESS-> {
                    //todo start main activity
                }
                else->{
                    //todo start login
                }
            }

        }

    }
}