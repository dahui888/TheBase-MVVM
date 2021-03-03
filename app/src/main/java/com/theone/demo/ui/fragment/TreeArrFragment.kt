package com.theone.demo.ui.fragment

import androidx.fragment.app.Fragment
import com.theone.mvvm.base.fragment.BaseTabInTitleFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.entity.QMUITabBean

class TreeArrFragment:BaseTabInTitleFragment<BaseViewModel>() {

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
        fragments.add(PlazaFragment())
        fragments.add(PlazaFragment())

    }

    override fun createObserver() {
    }

    override fun initData() {
    }

}