package com.theone.demo.ui.fragment

import android.os.Bundle
import com.theone.demo.R
import com.theone.demo.ui.adapter.TestAdapter
import com.theone.demo.viewmodel.TestViewModel
import com.theone.demo.entity.Brand
import com.theone.mvvm.base.fragment.BaseListFragment
import com.theone.mvvm.databinding.BaseTopbarFragmentBinding
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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TestFragment : BaseListFragment<Brand, TestAdapter, TestViewModel, BaseTopbarFragmentBinding>() {

    override fun createAdapter(): TestAdapter = TestAdapter()

    override fun initTopBar() {
        super.initTopBar()
        mTopBarLayout.setTitleWithBackBtn(R.string.app_name, this)
    }

    override fun initData() {

    }

}