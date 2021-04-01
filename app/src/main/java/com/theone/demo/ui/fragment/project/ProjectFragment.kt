package com.theone.demo.ui.fragment.project

import androidx.fragment.app.viewModels
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.viewmodel.ProjectViewModel
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.base.fragment.BaseTabInTitleFragment
import com.theone.mvvm.core.data.entity.QMUITabBean
import com.theone.mvvm.core.ext.qmui.addTab
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel


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
class ProjectFragment : BaseTabInTitleFragment<BaseViewModel>() {

    private val mRequestVm: ProjectViewModel by viewModels()

    override fun getRequestViewModel(): BaseRequestViewModel<List<ClassifyResponse>>?  = mRequestVm

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
        for (data in mRequestVm.getResponseLiveData().value!!) {
            tabs.addTab(data.name)
            fragments.add(
                ProjectItemFragment.newInstance(
                    data.id
                )
            )
        }
    }

    override fun isNeedChangeStatusBarMode(): Boolean = true

}