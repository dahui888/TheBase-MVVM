package com.theone.demo.ui.fragment

import android.view.View
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
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

    lateinit var mRecyclerPager: QMUICommonListItemView
    lateinit var mGroupListView: QMUICommonListItemView

    override fun initView(rootView: View) {
        mTopBar?.setTitle(R.string.app_name)
        mRecyclerPager = groupListView.createNormalItem("BaseRecyclerPagerFragment")
        mGroupListView = groupListView.createNormalItem("QMUIGroupListView")
        groupListView.addToGroup("ui",this, mRecyclerPager, mGroupListView)
    }

    override fun onLazyInit() {

    }

    override fun onClick(v: View?) {
        startFragment(
            when (v) {
                mRecyclerPager -> BrandFragment()
                else -> GroupListViewFragment()
            }
        )
    }

}