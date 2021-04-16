package com.theone.demo.ui.activity

import android.Manifest
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment
import com.theone.demo.ui.fragment.IndexFragment
import com.theone.demo.ui.fragment.sample.CustomViewFragment
import com.theone.demo.ui.fragment.sample.SampleFragment
import com.theone.mvvm.base.activity.BaseFragmentActivity
import rxhttp.wrapper.param.RxHttp

@DefaultFirstFragment(IndexFragment::class)
class MainActivity : BaseFragmentActivity() {

    private val PERMISSIONS_REQUEST_CODE = 10
    private val PERMISSIONS_REQUIRED =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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