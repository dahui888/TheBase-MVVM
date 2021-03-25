package com.theone.demo.ui.fragment

import android.view.View
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.databinding.FragmentSampleBinding
import com.theone.demo.ui.fragment.home.HomeFragment
import com.theone.mvvm.ext.qmui.addToGroup
import com.theone.mvvm.ext.qmui.createNormalItem
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.fragment.BaseCoreFragment


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
class SampleFragment : BaseCoreFragment<BaseViewModel, FragmentSampleBinding>(),
    View.OnClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_sample

    lateinit var mRecyclerPager: QMUICommonListItemView
    lateinit var mGroupListView: QMUICommonListItemView

    override fun initView(rootView: View) {
        getTopBar()?.setTitle(R.string.app_name)
        mBinding.groupListView.run {
            mRecyclerPager = createNormalItem("BaseRecyclerPagerFragment")
            mGroupListView = createNormalItem("QMUIGroupListView")
            addToGroup("ui", this@SampleFragment, mRecyclerPager, mGroupListView)
        }
    }

    override fun onLazyInit() {

    }

    override fun onClick(v: View?) {
        startFragment(
            when (v) {
                mRecyclerPager -> HomeFragment()
                else -> GroupListViewFragment()
            }
        )
    }

    override fun initData() {
    }

    override fun createObserver() {
    }

}