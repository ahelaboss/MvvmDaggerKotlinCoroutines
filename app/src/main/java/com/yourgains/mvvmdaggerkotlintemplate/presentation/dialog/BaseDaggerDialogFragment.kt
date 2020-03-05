package com.yourgains.mvvmdaggerkotlintemplate.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yourgains.mvvmdaggerkotlintemplate.R
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

abstract class BaseDaggerDialogFragment : DaggerDialogFragment() {

    companion object {
        private const val DIM_AMOUNT = 0.5f
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        val params = window?.attributes
        params?.dimAmount = DIM_AMOUNT
        window?.attributes = params
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return dialog
    }

    abstract fun getLayoutId(): Int

    protected fun notImplementedToast() {
        context?.let { Toast.makeText(it, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
    }

}