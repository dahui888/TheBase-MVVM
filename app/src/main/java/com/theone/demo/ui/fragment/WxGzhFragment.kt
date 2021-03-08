package com.theone.demo.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.viewmodel.WxGzhViewModel
import com.theone.mvvm.base.entity.QMUITabBean
import com.theone.mvvm.base.ext.showError
import com.theone.mvvm.base.ext.showLoading
import com.theone.mvvm.base.fragment.BaseTabInTitleFragment


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
 * @date 2021/3/4 0004
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class WxGzhFragment:BaseTabInTitleFragment<WxGzhViewModel>() {

    override fun isNeedChangeStatusBarMode(): Boolean  = true

    private lateinit var mResponse: List<ClassifyResponse>

    override fun onLazyInit() {
        mLoadSir.showLoading()
        mVm.requestServer()
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<Fragment>
    ) {

        for (data in mResponse) {
            tabs.add(QMUITabBean(data.name))
            fragments.add(WxGzhItemFragment.newInstance(data.id))
        }

    }

    override fun createObserver() {
       mVm.getResponse().observe(viewLifecycleOwner, Observer {
           mResponse = it
           startInit()
       })
        mVm.getErrorMsg().observe(viewLifecycleOwner, Observer {
            mLoadSir.showError(it)
        })
    }

    override fun initData() {

    }
}