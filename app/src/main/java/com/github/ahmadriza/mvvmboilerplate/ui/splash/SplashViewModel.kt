package com.github.ahmadriza.mvvmboilerplate.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class SplashViewModel @ViewModelInject constructor(
    private val repository: MainRepository
){

    val isLogin = repository.isLogin()

}