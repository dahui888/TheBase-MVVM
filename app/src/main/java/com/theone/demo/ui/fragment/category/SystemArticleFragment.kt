package com.theone.demo.ui.fragment.category

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.ui.fragment.BaseArticleFragment
import com.theone.demo.viewmodel.SystemArticleViewModel
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn


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
 * @describe 体系文章
 * @email 625805189@qq.com
 * @remark
 */
class SystemArticleFragment :
    BaseArticleFragment<SystemArticleViewModel>() {

    companion object {
        fun newInstance(data: ClassifyResponse): SystemArticleFragment {
            return  SystemArticleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("DATA", data)
                }
            }
        }
    }

    override fun initData() {
        val data = requireArguments().getParcelable<ClassifyResponse>("DATA")
        getTopBar()?.setTitleWithBackBtn(data!!.name,this)
        mViewModel.mId = data!!.id
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}