package com.theone.demo.ui.fragment.category

import com.theone.demo.ui.fragment.ArticleFragment
import com.theone.demo.viewmodel.PlazaViewModel

class PlazaFragment: ArticleFragment<PlazaViewModel>() {

    override fun createObserver() {
        super.createObserver()
        mAppVm.shareArticle.observeInFragment(this){
            onRefreshAuto()
        }
    }

}