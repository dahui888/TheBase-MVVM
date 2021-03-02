package com.theone.demo.ui.fragment

import android.view.View
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.R
import com.theone.demo.ui.adapter.HomeAdapter
import com.theone.demo.viewmodel.HomeViewModel
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.mvvm.ext.util.logE


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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class HomeFragment :
    BaseRecyclerPagerFragment<ArticleResponse, HomeAdapter, HomeViewModel, BaseRecyclerPagerFragmentBinding>() {

    override fun showTitleBar(): Boolean  = parentFragment is IndexFragment

    override fun createAdapter(): HomeAdapter = HomeAdapter()

    override fun initView(rootView: View) {
        super.initView(rootView)
        mRecyclerView.setBackgroundColor(
            ContextCompat.getColor(
                mActivity,
                R.color.qmui_config_color_background
            )
        )
    }

    override fun initData() {
        mTopBar?.setTitle(this.javaClass.simpleName)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//        val data = adapter.getItem(position) as Brand
//        startFragment(SeriesFragment.newInstance(data))
    }

}