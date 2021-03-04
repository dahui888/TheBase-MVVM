package com.theone.demo.ui.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.theone.demo.R
import com.theone.mvvm.base.fragment.BaseTabInTitleFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.base.entity.QMUITabBean
import com.theone.mvvm.base.ext.getWrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator

class ClassificationFragment : BaseTabInTitleFragment<BaseViewModel>() {

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<Fragment>
    ) {

        tabs.add(QMUITabBean("广场"))
        tabs.add(QMUITabBean("问答"))
        tabs.add(QMUITabBean("体系"))
        tabs.add(QMUITabBean("导航"))

        fragments.add(PlazaFragment())
        fragments.add(QAFragment())
        fragments.add(SystemFragment())
        fragments.add(NavFragment())

    }

    override fun getNavIndicator(context: Context): IPagerIndicator =
        getWrapPagerIndicator(context, R.color.qmui_config_color_background)

    override fun createObserver() {
    }

    override fun initData() {
    }

}