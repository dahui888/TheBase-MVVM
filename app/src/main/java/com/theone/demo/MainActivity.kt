package com.theone.demo

import android.os.Bundle
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.theone.mvvm.base.activity.BaseFragmentActivity

@DefaultFirstFragment(TestFragment::class)
class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

}