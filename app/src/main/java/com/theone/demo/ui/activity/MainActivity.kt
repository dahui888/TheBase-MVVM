package com.theone.demo.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.theone.demo.app.util.CookieUtil
import com.theone.demo.ui.fragment.IndexFragment
import com.theone.demo.ui.fragment.SampleFragment
import com.theone.mvvm.base.activity.BaseFragmentActivity
import com.theone.mvvm.base.ext.util.logE
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp

@DefaultFirstFragment(IndexFragment::class)
class MainActivity : BaseFragmentActivity() {

    private val PERMISSIONS_REQUEST_CODE = 10
    private val PERMISSIONS_REQUIRED =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CookieUtil.getCacheCookie().toString().logE()
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