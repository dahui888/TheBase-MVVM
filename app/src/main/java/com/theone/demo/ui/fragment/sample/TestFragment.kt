package com.theone.demo.ui.fragment.sample

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.theone.demo.R
import com.theone.demo.databinding.FragmentTestBinding
import com.theone.demo.ui.fragment.category.NavFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.fragment.BaseCoreFragment
import java.util.ArrayList


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
 * @date 2021/3/10 0010
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TestFragment:BaseCoreFragment<BaseViewModel,FragmentTestBinding>() {

    override fun isStatusBarLightMode(): Boolean  = true

    override fun showTopBar(): Boolean = false

    override fun getLayoutId(): Int  = R.layout.fragment_test

    override fun initView(rootView: View) {
        val adapter = MyPagerAdapter(childFragmentManager).apply {
            addFragment(NavFragment(),"Tab1")
            addFragment(NavFragment(),"Tab2")
        }
        mBinding.mViewPager
        mBinding.mViewPager.adapter = adapter
        mBinding.mTabLayout.setupWithViewPager(mBinding.mViewPager)

        Glide.with(this).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2586870947,764155106&fm=26&gp=0.jpg")
            .into(mBinding.cover)
    }

    inner class MyPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
        private val myFragments: MutableList<Fragment> = ArrayList<Fragment>()
        private val myFragmentTitles: MutableList<String> = ArrayList()

        fun addFragment(fragment: Fragment, title: String) {
            myFragments.add(fragment)
            myFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return myFragments[position]
        }

        override fun getCount(): Int {
            return myFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return myFragmentTitles[position]
        }
    }

    override fun onLazyInit() {

    }

    override fun initData() {
    }

    override fun createObserver() {
    }

}