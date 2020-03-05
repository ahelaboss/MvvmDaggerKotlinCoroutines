package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yourgains.mvvmdaggerkotlintemplate.R
import com.yourgains.mvvmdaggerkotlintemplate.common.Navigate
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel
import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.BaseFragment
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.FirstViewModel
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment() {

    private val viewModel: FirstViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FirstViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_first

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            itemsLiveData.observe(this@FirstFragment, Observer(this@FirstFragment::setItems))
            navigationGlobalEvent.observe(this@FirstFragment, Observer(this@FirstFragment::handleGlobalNavigation))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        f_first_btn_go_second.setOnClickListener { viewModel.onSecondPageClicked() }
    }

    private fun setItems(list: List<ItemUIModel>) {
        //TODO: Set items to adapter
    }

    private fun handleGlobalNavigation(event: Navigate.Global) {
        when(event){
            Navigate.Global.SecondPage -> navigate(R.id.action_firstFragment_to_secondFragment)
            else -> notImplementedToast()
        }
    }

}