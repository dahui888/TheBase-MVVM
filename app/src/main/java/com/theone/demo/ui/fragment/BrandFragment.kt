package com.theone.demo.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.demo.ui.adapter.TestAdapter
import com.theone.demo.viewmodel.BrandViewModel
import com.theone.demo.entity.Brand
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class BrandFragment :
    BaseRecyclerPagerFragment<Brand, TestAdapter, BrandViewModel, BaseRecyclerPagerFragmentBinding>() {

    override fun createAdapter(): TestAdapter = TestAdapter()

    override fun initData() {

    }

    override fun initTopBar(topBar: QMUITopBarLayout?) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val data = adapter.getItem(position) as Brand
        startFragment(SeriesFragment.newInstance(data))
    }

}