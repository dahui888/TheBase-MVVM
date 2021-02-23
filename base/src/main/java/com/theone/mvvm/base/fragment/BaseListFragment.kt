package com.theone.mvvm.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.mvvm.R
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.ext.getVmClazz
import com.theone.mvvm.ext.loadListData
import com.theone.mvvm.widge.SpacesItemDecoration


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
 * @date 2021/2/23 0023
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseListFragment
<T, AP : BaseQuickAdapter<T, *>, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BaseVmDbFragment<VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    lateinit var mAdapter: BaseQuickAdapter<T, *>
    lateinit var mTopBarLayout: QMUITopBarLayout
    lateinit var mRefreshLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView

    enum class Type {
        LIST,
        GRID,
        STAGGERED
    }

    override fun getLayoutId(): Int = R.layout.base_topbar_fragment

    abstract fun createAdapter(): AP

    open fun getColumn() = 2
    open fun getType() = Type.LIST
    open fun getSpace() = 12

    override fun onLazyInit() {
        mRefreshLayout.isRefreshing = true
        mViewModel.requestServer()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initTopBar()
        initAdapter()
        initRecyclerView()
        initPullRefreshLayout()
    }

    open fun initTopBar() {
        mTopBarLayout = mDataBind.root.findViewById(R.id.topBarLayout)
    }

    open fun initRecyclerView() {
        mRecyclerView = mDataBind.root.findViewById(R.id.recyclerView)
        mRecyclerView.run {
            layoutManager = initLayoutManager(getType())
            addItemDecoration(getSpacesItemDecoration())
            adapter = mAdapter
        }
    }

    open fun initLayoutManager(type: Type): RecyclerView.LayoutManager {
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = when (type) {
            Type.LIST -> LinearLayoutManager(mActivity)
            Type.GRID -> GridLayoutManager(mActivity, getColumn())
            Type.STAGGERED -> {
                val m = StaggeredGridLayoutManager(getColumn(), StaggeredGridLayoutManager.VERTICAL)
                m.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                return m
            }

        }
        return layoutManager
    }

    open fun getSpacesItemDecoration(): SpacesItemDecoration {
        val space = QMUIDisplayHelper.dp2px(mActivity, getSpace())
        return SpacesItemDecoration(
            if (getType() == Type.LIST) 1 else getColumn(),
            mAdapter.headerLayoutCount,
            space
        )
    }

    open fun initAdapter() {
        mAdapter = createAdapter()
        mAdapter.loadMoreModule.setOnLoadMoreListener(this)
    }

    open fun initPullRefreshLayout() {
        mRefreshLayout = mDataBind.root.findViewById(R.id.swipeRefresh)
        mRefreshLayout.run {
            setOnRefreshListener(this@BaseListFragment)
        }
    }

    override fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this, 2))
    }

    override fun createObserver() {
        mViewModel.getResponse().observe(viewLifecycleOwner, Observer {
            mAdapter.loadListData(mViewModel)
            mRefreshLayout.isRefreshing = false
        })
    }

    override fun onRefresh() {
        mViewModel.mPage = 1
        mViewModel.requestServer()
    }

    override fun onLoadMore() {
        mViewModel.requestServer()
    }

}