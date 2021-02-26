package com.theone.demo.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.demo.entity.Brand
import com.theone.demo.entity.Series
import com.theone.demo.ui.adapter.SeriesAdapter
import com.theone.demo.viewmodel.SeriesViewModel
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.mvvm.ext.setTitleWithBackBtn


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
 * @date 2021/2/25 0025
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SeriesFragment : BaseRecyclerPagerFragment<Series, SeriesAdapter, SeriesViewModel, BaseRecyclerPagerFragmentBinding>() {

    companion object {
        fun newInstance(brand: Brand): SeriesFragment {
            val fragment = SeriesFragment()
            val bundle = Bundle()
            bundle.putParcelable("DATA", brand)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createAdapter(): SeriesAdapter = SeriesAdapter()

    override fun initData() {
        val mBrand = requireArguments().getParcelable("DATA") as Brand
        mTopBar?.setTitleWithBackBtn(this.javaClass.simpleName,this)
        mVm.mBrandId = mBrand.id
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}