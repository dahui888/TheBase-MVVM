package com.theone.demo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.theone.demo.R
import com.theone.demo.app.util.checkLogin
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.ArticleViewModel
import com.theone.demo.viewmodel.EventViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.showFailDialog


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
    SpacePagerListFragment<ArticleResponse, VM>(),
    OnItemChildClickListener {

    private val mEventVm: EventViewModel by lazy { getAppViewModel<EventViewModel>() }
    private val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    override fun getViewModelIndex(): Int = 0

    override fun createAdapter(): ArticleAdapter = ArticleAdapter()

    override fun initAdapter() {
        super.initAdapter()
        mAdapter.addChildClickViewIds(R.id.collection)
        mAdapter.setOnItemChildClickListener(this)
    }

    override fun initData() {

    }

    override fun createObserver() {
        super.createObserver()
        // 监听用户登录、登出时，改变收藏
        mAppVm.userInfo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                it.collectIds.forEach { id ->
                    for (item in mAdapter.data) {
                        if (id.toInt() == item.id) {
                            item.collect = true
                            break
                        }
                    }
                }
            } else {
                for (item in mAdapter.data) {
                    item.collect = false
                }
            }
            mAdapter.notifyDataSetChanged()
        })
        mEventVm.collectEvent.observeInFragment(this, Observer {
            for (index in mAdapter.data.indices) {
                if (mAdapter.data[index].id == it.id) {
                    mAdapter.data[index].collect = it.collect
                    mAdapter.notifyItemChanged(index+mAdapter.headerLayoutCount)
                    break
                }
            }
        })
        mViewModel.getCollectionError().observe(viewLifecycleOwner, Observer {
            showFailDialog(it)
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val article = adapter.getItem(position) as ArticleResponse
        startFragment(WebExplorerFragment.newInstance(article))
    }

    override fun onItemChildClick(
        adapter: BaseQuickAdapter<*, *>, view: View, position: Int
    ) {
        checkLogin {
            val article = adapter.getItem(position) as ArticleResponse
            mViewModel.collection(article,mEventVm)
        }
    }

}