package com.theone.demo.ui.fragment

import android.os.Bundle
import android.view.View
import com.theone.demo.viewmodel.SearchResultModel
import com.theone.demo.viewmodel.WxGzhItemViewModel
import com.theone.mvvm.base.ext.qmui.setTitleWithBackBtn


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
    ArticleFragment<SearchResultModel>() {

    companion object {
        fun newInstance(key: String): SearchResultFragment {
            val fragment = SearchResultFragment()
            val bundle = Bundle()
            bundle.putString("DATA", key)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView(rootView: View) {
        val key = requireArguments().getString("DATA","")
        mViewModel.mKey = key
        super.initView(rootView)
        getTopBar()?.setTitleWithBackBtn(key,this)
    }

    /**
     * 更改进出动画效果 QMUIFragment提供
     */
    override fun onFetchTransitionConfig(): TransitionConfig  = SCALE_TRANSITION_CONFIG

}