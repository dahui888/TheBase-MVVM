package com.theone.demo.ui.fragment.gzh

import androidx.lifecycle.Observer
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.viewmodel.WxGzhViewModel
import com.theone.mvvm.base.data.entity.QMUITabBean
import com.theone.mvvm.base.ext.showErrorPage
import com.theone.mvvm.base.ext.showLoadingPage
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
        showLoadingPage()
        mViewModel.requestServer()
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {

        for (data in mResponse) {
            tabs.add(QMUITabBean(data.name))
            fragments.add(
                WxGzhItemFragment.newInstance(
                    data.id
                )
            )
        }
    }

    override fun createObserver() {
       mViewModel.getResponseLiveData().observeInFragment(this, Observer {
           mResponse = it
           startInit()
       })
        mViewModel.getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
            showErrorPage(it)
        })
    }

    override fun initData() {

    }
}