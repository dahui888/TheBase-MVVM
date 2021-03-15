package com.theone.demo.ui.fragment.project

import android.view.View
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.viewmodel.ProjectViewModel
import com.theone.mvvm.base.fragment.BaseTabInTitleFragment
import com.theone.mvvm.base.entity.QMUITabBean
import com.theone.mvvm.base.ext.*


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
class ProjectFragment : BaseTabInTitleFragment<ProjectViewModel>() {

    private lateinit var mResponse: List<ClassifyResponse>

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun onLazyInit() {
        showLoadingPage()
        mViewModel.requestServer()
    }

    override fun onReLoad() {
        onLazyInit()
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
        for (data in mResponse) {
            tabs.add(QMUITabBean(data.name))
            fragments.add(
                ProjectItemFragment.newInstance(
                    data.id
                )
            )
        }
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
//        getTopBar()?.run {
//            addRightImageButton(R.drawable.mz_titlebar_ic_list_dark,R.id.topbar_right_button1).setOnClickListener{
//
//            }
//        }
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