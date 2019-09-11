package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import com.yourgains.mvvmdaggerkotlintemplate.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    @LayoutRes
    abstract fun getLayoutId(): Int

    private fun getBaseActivity(): BaseActivity? =
        if (activity is BaseActivity) activity as BaseActivity else null

    protected fun notImplementedToast() {
        activity?.let { Toast.makeText(it, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
    }

    fun navigate(id: Int, bundle: Bundle? = null) {
        val navController = getBaseActivity()?.getNavController()
        val action = navController?.currentDestination?.getAction(id)
        if (action != null) navController.navigate(id, bundle)
    }

    fun navigate(directions: NavDirections) {
        getBaseActivity()?.getNavController()?.navigate(directions)
    }

    fun navigateUp() {
        getBaseActivity()?.getNavController()?.navigateUp()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    protected fun showAppBar() {
        getBaseActivity()?.showAppBar()
    }

    protected fun hideAppBar() {
        getBaseActivity()?.hideAppBar()
    }

    protected fun setAppBarTitle(@StringRes titleResId: Int) {
        getBaseActivity()?.setAppBarTitle(titleResId)
    }

    protected fun setAppBarTitle(title: String) {
        getBaseActivity()?.setAppBarTitle(title)
    }

    protected fun lockDrawerLayout() {
        getBaseActivity()?.lockDrawerLayout()
    }

    protected fun unlockDrawerLayout() {
        getBaseActivity()?.unlockDrawerLayout()
    }
}