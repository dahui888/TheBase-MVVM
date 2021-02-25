package com.theone.demo.ui.activity

import android.os.Bundle
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.theone.demo.ui.fragment.BrandFragment
import com.theone.demo.ui.fragment.SampleFragment
import com.theone.demo.ui.fragment.TestFragment
import com.theone.mvvm.base.activity.BaseFragmentActivity
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp

@DefaultFirstFragment(SampleFragment::class)
class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxHttp.setOnParamAssembly { p: Param<*>? ->
            p!!.add("key", "0b6d74779b85bc36e020ab6697813714") //添加公共参数
        }
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

}