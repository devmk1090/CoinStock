package com.devkproject.coinstock.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

fun AppCompatActivity.replaceFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    tag: String? = null,
    addToBackStack: Boolean = true,
    popBackStack: Boolean = false
) {
    if (popBackStack) {
        this.supportFragmentManager.popBackStack()
    }

    this.supportFragmentManager.commit {  }
}