package com.theone.demo.ui.activity

import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.theone.demo.ui.fragment.LoginFragment


@DefaultFirstFragment(LoginFragment::class)
class LoginActivity:QMUIFragmentActivity() {
}