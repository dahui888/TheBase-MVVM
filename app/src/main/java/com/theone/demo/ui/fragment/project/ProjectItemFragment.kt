package com.theone.demo.ui.fragment.project

import android.os.Bundle
import com.theone.demo.ui.fragment.BaseArticleFragment
import com.theone.demo.viewmodel.ProjectItemViewModel


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
 * @date 2021/3/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ProjectItemFragment :
    BaseArticleFragment<ProjectItemViewModel>() {

    companion object {
        fun newInstance(id: Int): ProjectItemFragment {
            return  ProjectItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(DATA, id)
                }
            }
        }
        const val DATA = "data"
    }

    override fun initData() {
        mViewModel.mId = requireArguments().getInt(DATA)
    }

}