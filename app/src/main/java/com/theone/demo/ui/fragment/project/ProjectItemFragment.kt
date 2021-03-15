package com.theone.demo.ui.fragment.project

import android.os.Bundle
import com.theone.demo.ui.fragment.ArticleFragment
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
    ArticleFragment<ProjectItemViewModel>() {

    companion object {
        fun newInstance(id: Int): ProjectItemFragment {
            val fragment =
                ProjectItemFragment()
            val bundle = Bundle()
            bundle.putInt("DATA", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        val id = requireArguments().getInt("DATA")
        mViewModel.mId = id
    }

}