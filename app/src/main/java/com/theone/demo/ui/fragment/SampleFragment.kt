package com.theone.demo.ui.fragment

import android.view.View
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.demo.R
import com.theone.mvvm.base.fragment.BaseFragment
import com.theone.mvvm.ext.qmui.addToGroup
import com.theone.mvvm.ext.qmui.createNormalItem
import kotlinx.android.synthetic.main.fragment_sample.*


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
class SampleFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_sample

    override fun onViewCreated(rootView: View) {
        val recyclerPager = groupListView.createNormalItem("BaseRecyclerPagerFragment")
        groupListView.addToGroup(this, recyclerPager)
    }

    override fun initTopBar(topBar: QMUITopBarLayout?) {
        topBar?.setTitle(R.string.app_name)
    }

    override fun onLazyInit() {

    }

    override fun onClick(v: View?) {
        startFragment(BrandFragment())
    }

}