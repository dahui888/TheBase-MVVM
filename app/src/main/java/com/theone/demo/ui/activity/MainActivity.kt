package com.theone.demo.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.theone.demo.ui.fragment.IndexFragment
import com.theone.demo.ui.fragment.SampleFragment
import com.theone.mvvm.base.activity.BaseFragmentActivity
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp

@DefaultFirstFragment(IndexFragment::class)
class MainActivity : BaseFragmentActivity() {

    private val PERMISSIONS_REQUEST_CODE = 10
    private val PERMISSIONS_REQUIRED =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxHttp.setOnParamAssembly { p: Param<*>? ->
            p!!.add("key", "0b6d74779b85bc36e020ab6697813714") //添加公共参数
        }
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        requestPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            PERMISSIONS_REQUIRED,
            PERMISSIONS_REQUEST_CODE
        )
    }

}