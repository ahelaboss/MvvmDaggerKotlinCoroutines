package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yourgains.mvvmdaggerkotlintemplate.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var navController: NavController? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (getNavContainerId() != -1) navController = Navigation.findNavController(this, getNavContainerId())
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        this.toolbar = toolbar
//        this.toolbar?.setTitleTextAppearance(this, R.style.ToolbarText)
        super.setSupportActionBar(toolbar)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun getNavContainerId(): Int = -1

    protected open fun getDrawerToggle(): ActionBarDrawerToggle? = null

    fun getNavController(): NavController? = navController

    open fun lockDrawerLayout() {
        //Do nothing
    }

    open fun unlockDrawerLayout() {
        //Do nothing
    }

    protected fun notImplementedToast() {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun showAppBar() {
        supportActionBar?.show()
    }

    fun hideAppBar() {
        supportActionBar?.hide()
    }

    fun setBackButton(isEnabled: Boolean) {
        if (isEnabled) showAppBar()
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(isEnabled)
        getDrawerToggle()?.syncState()
//        if (!isEnabled) toolbar?.setNavigationIcon(R.drawable.ic_menu)
//        else supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    fun setAppBarTitle(@StringRes titleResId: Int) {
        supportActionBar?.setTitle(titleResId)
    }

    fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }
}