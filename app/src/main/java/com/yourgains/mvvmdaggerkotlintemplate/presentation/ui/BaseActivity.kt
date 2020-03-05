package com.yourgains.mvvmdaggerkotlintemplate.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.yourgains.mvvmdaggerkotlintemplate.R
import com.yourgains.mvvmdaggerkotlintemplate.common.event.NetworkStateEvent
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent
import com.yourgains.mvvmdaggerkotlintemplate.common.gone
import com.yourgains.mvvmdaggerkotlintemplate.common.visible
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.BaseProgressViewModel
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.BaseViewModel
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.KeyboardViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var keyboardViewModel: KeyboardViewModel

    private var navController: NavController? = null
    private var toolbar: Toolbar? = null

    private var keyboardListenersAttached = false

    private val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        getRootLayout()?.let {
            var heightDiff = it.rootView.height - it.height
            val navBarId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            val statusBarId = resources.getIdentifier("status_bar_height", "dimen", "android")
            takeIf { navBarId > 0 }?.let { heightDiff -= resources.getDimensionPixelSize(navBarId) }
            takeIf { statusBarId > 0 }?.let {
                heightDiff -= resources.getDimensionPixelSize(statusBarId)
            }
            if (heightDiff <= 0) onHideKeyboard()
            else onShowKeyboard()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (getNavContainerId() != -1) navController =
            Navigation.findNavController(this, getNavContainerId())

        getViewModel()?.apply {
            val owner = this@BaseActivity
            networkConnectionGlobalEvent.observe(owner, Observer(::handleNetworkStateEvent))
        }

        getProgressViewModel()?.apply {
            progressGlobalEvent.observe(this@BaseActivity, Observer(::handleProgressBarEvent))
        }

        keyboardViewModel = ViewModelProvider(this).get(KeyboardViewModel::class.java)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        this.toolbar = toolbar
//        this.toolbar?.setTitleTextAppearance(this, R.style.ToolbarText)
        super.setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        getViewModel()?.onResume()
    }

    override fun onPause() {
        super.onPause()
        getViewModel()?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (keyboardListenersAttached)
            getRootLayout()?.viewTreeObserver?.removeOnGlobalLayoutListener(keyboardLayoutListener)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun getProgressLayout(): View? = null

    protected open fun getNavContainerId(): Int = -1

    protected open fun getDrawerToggle(): ActionBarDrawerToggle? = null

    fun getNavController(): NavController? = navController

    protected open fun getViewModel(): BaseViewModel? = null

    private fun getProgressViewModel(): BaseProgressViewModel? =
        getViewModel() as? BaseProgressViewModel?

    protected open fun getNavigationItemSelectedListener(): NavigationView.OnNavigationItemSelectedListener? =
        null

    protected open fun getNavigationView(): NavigationView? = null

    protected open fun getRootLayout(): ViewGroup? = null

    open fun lockDrawerLayout() {
        //Do nothing
    }

    open fun unlockDrawerLayout() {
        //Do nothing
    }

    open fun showProgressBar() {
        getProgressLayout()?.visible()
    }

    open fun hideProgressBar() {
        getProgressLayout()?.gone()
    }

    open fun showNetworkConnection() {
//        getCenteredTitleToolbar()?.showNetworkConnecting()
    }

    open fun showNetworkErrorDialog() {
//        ErrorDialogFragment.show(
//            supportFragmentManager,
//            getString(R.string.error_no_internet_message),
//            getString(R.string.error_no_internet_title)
//        )
    }

    open fun hideNetworkConnection() {
//        getCenteredTitleToolbar()?.hideNetworkConnecting()
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

    private fun handleProgressBarEvent(event: StateEvent) {
        when (event) {
            StateEvent.SHOW -> showProgressBar()
            else -> hideProgressBar()
        }
    }

    private fun handleNetworkStateEvent(event: NetworkStateEvent) {
        when (event) {
            NetworkStateEvent.SHOW -> showNetworkConnection()
            NetworkStateEvent.ERROR_DIALOG -> showNetworkErrorDialog()
            else -> hideNetworkConnection()
        }
    }

    protected fun attachKeyboardListeners() {
        if (keyboardListenersAttached) return
        getRootLayout()?.let {
            it.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
            keyboardListenersAttached = true
        }
    }

    protected open fun onShowKeyboard() {
        keyboardViewModel.showKeyboard()
    }

    protected open fun onHideKeyboard() {
        keyboardViewModel.hideKeyboard()
    }
}