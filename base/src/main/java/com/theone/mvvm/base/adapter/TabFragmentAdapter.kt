package com.theone.mvvm.base.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter


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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TabFragmentAdapter(fm:FragmentManager,private val mFragments : List<QMUIFragment>) :
    QMUIFragmentPagerAdapter(fm) {

    private var mChildCount = 0

    override fun getCount(): Int  = mFragments.size

    override fun getItemPosition(`object`: Any): Int {
        return if (mChildCount == 0) {
            PagerAdapter.POSITION_NONE
        } else super.getItemPosition(`object`)
    }

    override fun createFragment(position: Int): QMUIFragment  = mFragments[position]

    override fun notifyDataSetChanged() {
        mChildCount = count
        super.notifyDataSetChanged()
    }
}