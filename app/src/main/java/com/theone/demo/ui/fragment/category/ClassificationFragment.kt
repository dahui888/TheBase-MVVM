package com.theone.demo.ui.fragment.category

import android.content.Context
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.fragment.BaseTabInTitleFragment
import com.theone.mvvm.core.data.entity.QMUITabBean
import com.theone.mvvm.core.ext.getWrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator

class ClassificationFragment : BaseTabInTitleFragment<BaseViewModel>() {

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
        with(tabs){
            add(QMUITabBean("广场"))
            add(QMUITabBean("问答"))
            add(QMUITabBean("体系"))
            add(QMUITabBean("导航"))
        }
        with(fragments){
            add(PlazaFragment())
            add(QAFragment())
            add(SystemFragment())
            add(NavFragment())
        }
    }

    override fun getNavIndicator(context: Context): IPagerIndicator =
        getWrapPagerIndicator(context, R.color.qmui_config_color_background)

    override fun createObserver() {
    }

    override fun initData() {
    }

}