package com.theone.demo.ui.fragment.search

import android.os.Bundle
import android.view.View
import com.theone.demo.ui.fragment.BaseArticleFragment
import com.theone.demo.viewmodel.SearchResultModel
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn
import com.theone.common.constant.BundleConstant


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
class SearchResultFragment :
    BaseArticleFragment<SearchResultModel>() {

    companion object {
        fun newInstance(key: String): SearchResultFragment {
            return SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putString(BundleConstant.DATA, key)
                }
            }
        }
    }

    override fun initView(rootView: View) {
        val key = requireArguments().getString(BundleConstant.DATA,"")
        mViewModel.mKey = key
        super.initView(rootView)
        getTopBar()?.setTitleWithBackBtn(key,this)
    }

    /**
     * 更改进出动画效果 QMUIFragment提供
     */
    override fun onFetchTransitionConfig(): TransitionConfig  = SCALE_TRANSITION_CONFIG

}