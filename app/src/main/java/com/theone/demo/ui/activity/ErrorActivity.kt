package com.theone.demo.ui.activity

import android.os.Bundle
import android.view.View
import com.theone.demo.R
import com.theone.demo.databinding.ActivityErrorBinding
import com.theone.demo.databinding.FragmentTestBinding
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021-03-31 15:21
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ErrorActivity : BaseVmDbActivity<BaseViewModel, ActivityErrorBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_error

    override fun initView(root:View) {
        getTopBar()?.run {
            setTitle(R.string.app_name)
        }
    }

    override fun initData() {
    }

    override fun createObserver() {
    }


}