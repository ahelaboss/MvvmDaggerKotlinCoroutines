package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main

import androidx.lifecycle.ViewModelProviders
import com.yourgains.mvvmdaggerkotlintemplate.R
import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.BaseActivity
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getNavContainerId(): Int = R.id.a_main_nav_container

}
