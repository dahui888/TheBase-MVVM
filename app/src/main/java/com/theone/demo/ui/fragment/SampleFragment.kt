package com.theone.demo.ui.fragment

import android.os.Bundle
import com.theone.demo.R
import com.theone.demo.databinding.FragmentTestBinding
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.setTitleWithBackBtn
import com.theone.mvvm.ext.showLoading
import kotlinx.android.synthetic.main.fragment_test.*


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
 * @date 2021/2/24 0024
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SampleFragment :BaseVmDbFragment<BaseViewModel,FragmentTestBinding>() {

    override fun createObserver() {
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView(savedInstanceState: Bundle?) {
        initLoadSer(status_layout){

        }
        top_layout.setTitleWithBackBtn(R.string.app_name,this)
    }

    override fun onLazyInit() {
        mLoader.showLoading()
    }

}