package com.theone.demo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.ProjectItemViewModel
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.mvvm.ext.getVmClazz


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
abstract class ArticleFragment<VM : BaseListViewModel<ArticleResponse>> :
    BaseDemoPagerListFragment<ArticleResponse, ArticleAdapter, VM, BaseRecyclerPagerFragmentBinding>() {

    override fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    override fun createAdapter(): ArticleAdapter = ArticleAdapter()

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}