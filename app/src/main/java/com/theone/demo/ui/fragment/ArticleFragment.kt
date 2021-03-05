package com.theone.demo.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.ArticleViewModel
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding


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
 * @date 2021/3/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class ArticleFragment<VM : ArticleViewModel> :
    BaseDemoPagerListFragment<ArticleResponse, ArticleAdapter, VM, BaseRecyclerPagerFragmentBinding>(),
    OnItemChildClickListener {

    override fun getViewModelIndex(): Int = 0

    override fun createAdapter(): ArticleAdapter = ArticleAdapter()

    override fun initAdapter() {
        super.initAdapter()
        mAdapter.addChildClickViewIds(R.id.collection)
        mAdapter.setOnItemChildClickListener(this)
    }

    override fun initData() {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int
    ) {
        val article = adapter.getItem(position) as ArticleResponse
        " click collection, article id = ${article.id}".logE()
    }

}