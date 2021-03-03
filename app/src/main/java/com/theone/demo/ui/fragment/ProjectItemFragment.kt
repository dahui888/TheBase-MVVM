package com.theone.demo.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.ProjectItemViewModel
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding


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
    BaseDemoPagerListFragment<ArticleResponse, ArticleAdapter, ProjectItemViewModel, BaseRecyclerPagerFragmentBinding>() {

    companion object {
        fun newInstance(id: Int): ProjectItemFragment {
            val fragment = ProjectItemFragment()
            val bundle = Bundle()
            bundle.putInt("DATA", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createAdapter(): ArticleAdapter = ArticleAdapter()


    override fun initData() {
        val id = requireArguments().getInt("DATA")
        mVm.mId = id
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}