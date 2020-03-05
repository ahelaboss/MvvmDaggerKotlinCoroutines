package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.yourgains.mvvmdaggerkotlintemplate.R
import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.BaseActivity
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.BaseViewModel
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.MainViewModel
import kotlinx.android.synthetic.main.progress.*

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getNavContainerId(): Int = R.id.a_main_nav_container

    override fun getProgressLayout(): View? = progress_fl

    override fun getViewModel(): BaseViewModel? = viewModel

}
