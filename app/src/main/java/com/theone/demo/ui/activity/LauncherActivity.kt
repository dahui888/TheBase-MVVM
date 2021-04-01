package com.theone.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.arch.QMUILatestVisit
import com.theone.demo.R
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.app.widge.TypeTextView
import com.theone.demo.databinding.ActivityLauncherBinding
import com.theone.mvvm.base.activity.BaseQMUIActivity
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

class LauncherActivity : BaseVmDbActivity<BaseViewModel, ActivityLauncherBinding>(),
    TypeTextView.OnTypeViewListener {

    private val mTypes: Array<String> by lazy {
        resources.getStringArray(R.array.launcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        super.onCreate(savedInstanceState)
    }

    override fun showTopBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.activity_launcher

    override fun initView(root: View) {
        val tips = mTypes[(mTypes.indices).random()]
        mBinding.tvType.run {
            if (CacheUtil.isOpenLauncherText()) {
                setOnTypeViewListener(this@LauncherActivity)
                start(tips, 220)
            } else {
                text = tips
                startToMain(3000)
            }
        }
    }

    override fun initData() {
    }

    override fun createObserver() {
    }


    override fun onTypeStart() {

    }

    override fun onTypeOver() {
        startToMain(800)
    }

    private fun startToMain(delay: Long) {
        getContentView().postDelayed({
            var intent = QMUILatestVisit.intentOfLatestVisit(this)
            if (intent == null) {
                intent = Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, delay)
    }

}