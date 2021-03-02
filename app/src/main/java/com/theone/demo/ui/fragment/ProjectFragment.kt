package com.theone.demo.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.viewmodel.ProjectViewModel
import com.theone.mvvm.base.fragment.BaseFragment
import com.theone.mvvm.base.fragment.BaseTitleTabFragment
import com.theone.mvvm.entity.QMUITabBean
import com.theone.mvvm.ext.showError
import com.theone.mvvm.ext.showLoading


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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ProjectFragment : BaseTitleTabFragment<ProjectViewModel>() {

    private lateinit var mResponse: List<ClassifyResponse>

    override fun showTitleBar(): Boolean = true

    override fun onLazyInit() {
        mLoadSir.showLoading()
        mVm.requestServer()
    }

    override fun onReLoad() {
        onLazyInit()
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<Fragment>
    ) {
        for (data in mResponse) {
            tabs.add(QMUITabBean(data.name))
            fragments.add(HomeFragment())
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
        mTopBar?.setTitle("项目")
    }

}