package com.theone.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.arch.QMUILatestVisit
import com.theone.demo.R
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.app.util.RxHttpManager
import com.theone.demo.app.widget.TypeTextView
import com.theone.demo.databinding.ActivityLauncherBinding
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.viewmodel.BaseViewModel

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
        // 再次安装后请求时要清除，不然会读取到
        if(CacheUtil.isFirst()){
            RxHttpManager.clearCache()
            CacheUtil.isEnterApp()
        }
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