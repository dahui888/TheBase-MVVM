package com.theone.demo.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.PlazaViewModel
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding

class PlazaFragment:BaseDemoPagerListFragment<ArticleResponse,ArticleAdapter, PlazaViewModel,BaseRecyclerPagerFragmentBinding>() {

    override fun createAdapter(): ArticleAdapter  = ArticleAdapter()

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}