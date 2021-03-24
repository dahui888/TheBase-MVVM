package com.theone.mvvm.core.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.theone.mvvm.core.R
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.ext.showLoadingPage
import com.theone.mvvm.core.viewmodel.BaseListViewModel
import kotlinx.android.synthetic.main.base_recycler_pager_fragment.*


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
 * @describe RecyclerView分页显示基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseRecyclerPagerFragment
<T, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BaseCoreFragment<VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, OnItemClickListener {

    lateinit var mAdapter: BaseQuickAdapter<T, *>

    override fun getLayoutId(): Int = R.layout.base_recycler_pager_fragment
    abstract fun createAdapter(): BaseQuickAdapter<T, *>

    protected open fun getRecyclerView(): RecyclerView = recyclerView
    protected open fun getRefreshLayout(): SwipeRefreshLayout = swipeRefresh

    protected open fun getLayoutManagerType(): LayoutManagerType = LayoutManagerType.LIST
    protected open fun getSpanCount(): Int = 1
    protected open fun getItemSpace(): Int = 0

    override fun initView(rootView: View) {
        initAdapter()
        initRecyclerView()
        initPullRefreshLayout()
    }

    override fun onLazyInit() {
        onFirstLoading()
    }

    override fun onReLoad() {
        onFirstLoading()
    }

    open fun initAdapter() {
        mAdapter = createAdapter().apply {
            loadMoreModule.setOnLoadMoreListener(this@BaseRecyclerPagerFragment)
            setOnItemClickListener(this@BaseRecyclerPagerFragment)
        }
    }

    open fun initRecyclerView() {
        getRecyclerView().run {
            getSpacesItemDecoration()?.run {
                addItemDecoration(this)
            }
            layoutManager = getLayoutManager(getLayoutManagerType())
            adapter = mAdapter
        }
    }

    open fun getLayoutManager(type: LayoutManagerType?): RecyclerView.LayoutManager {
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = when (type) {
            LayoutManagerType.GRID -> GridLayoutManager(mActivity, getSpanCount())
            LayoutManagerType.STAGGERED -> {
                val m = StaggeredGridLayoutManager(
                    getSpanCount(),
                    StaggeredGridLayoutManager.VERTICAL
                )
                m.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                return m
            }
            else -> LinearLayoutManager(mActivity)
        }
        return layoutManager
    }

    open fun getSpacesItemDecoration(): com.theone.mvvm.core.widge.SpacesItemDecoration? {
        val space = QMUIDisplayHelper.dp2px(mActivity, getItemSpace())
        return com.theone.mvvm.core.widge.SpacesItemDecoration(
            if (getLayoutManagerType() == LayoutManagerType.LIST) 1 else getSpanCount(),
            mAdapter.headerLayoutCount,
            space
        )
    }

    /**
     * 初始化下拉刷新
     */
    open fun initPullRefreshLayout() {
        getRefreshLayout().run {
            isEnabled = false
            setOnRefreshListener(this@BaseRecyclerPagerFragment)
        }
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@BaseRecyclerPagerFragment, Observer {
                com.theone.mvvm.core.ext.loadListData(
                    mViewModel,
                    mAdapter,
                    mLoadSir
                )
            })
            getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
                com.theone.mvvm.core.ext.loadListError(
                    it,
                    mViewModel,
                    mAdapter,
                    mLoadSir
                )
            })
            getFinallyLiveData().observe(viewLifecycleOwner, Observer {
                onRefreshComplete()
            })
        }
    }

    /**
     * 第一次加载和刷新还是有区别的，所以这里分开
     */
    open fun onFirstLoading() {
        showLoadingPage()
        getRecyclerView().scrollToPosition(0)
        mViewModel.isFirst = true
        mViewModel.requestNewData()
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        getRefreshLayout().isRefreshing = true
        mViewModel.isFresh = true
        mViewModel.requestNewData()
    }

    /**
     * 回调需要自动刷新时
     */
    fun onRefreshAuto() {
        if (mAdapter.data.size == 0) {
            onFirstLoading()
        } else {
            onRefresh()
        }
    }

    /**
     * 刷新完成时的操作
     */
    open fun onRefreshComplete() {
        getRefreshLayout().run {
            isEnabled = true
            isRefreshing = false
        }
    }


    /**
     * 加载更多(page已经在 loadListData 方法里增加了，所以这里只管请求数据）
     * page的更改绝对不能放在这里处理，因为加载更多可能存在失败的情况。
     */
    override fun onLoadMore() {
        mViewModel.requestServer()
    }

    override fun getViewModelIndex(): Int = 1

}