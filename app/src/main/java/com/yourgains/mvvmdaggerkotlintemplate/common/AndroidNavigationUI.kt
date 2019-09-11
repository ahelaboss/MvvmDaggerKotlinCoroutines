package com.yourgains.mvvmdaggerkotlintemplate.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference

object AndroidNavigationUI {

    fun setupWithNavController(
        navigationView: NavigationView,
        navController: NavController,
        listener: NavigationView.OnNavigationItemSelectedListener? = null
    ) {
        navigationView.setNavigationItemSelectedListener { item ->
            navController.currentDestination?.let {
                navController.popBackStack(it.id, true)
            }
            val handled = listener?.onNavigationItemSelected(item)?.takeIf { it }
                ?: NavigationUI.onNavDestinationSelected(item, navController)

            if (handled) {
                val parent = navigationView.parent
                if (parent is DrawerLayout) {
                    parent.closeDrawer(navigationView)
                }
            }

            handled
        }
        val weakReference = WeakReference(navigationView)
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                } else {
                    val menu = view.menu
                    for (i in 0 until menu.size()) {
                        val item = menu.getItem(i)
                        item.isChecked = matchDestination(destination, item.itemId)
                    }
                }
            }
        })
    }

    internal fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
        var currentDestination: NavDestination? = destination
        if (currentDestination?.id != destId) {
            currentDestination = currentDestination?.parent
        }
        return currentDestination?.id == destId
    }
}