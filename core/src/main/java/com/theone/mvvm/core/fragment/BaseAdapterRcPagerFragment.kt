package com.theone.mvvm.core.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.LoadMoreModule
import com.theone.common.ext.dp2px
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.ext.*
import com.theone.mvvm.core.viewmodel.BaseListViewModel
import com.theone.mvvm.core.widge.SpacesItemDecoration


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
 * @remark 给定了默认的Adapter初始化和RecyclerView初始化, 需要自定义下拉刷新控件继承此类
 */
abstract class BaseAdapterRcPagerFragment
<T, VM : BaseViewModel, DB : ViewDataBinding>
    : BaseRecyclerViewFragment<VM, DB>()
   , OnLoadMoreListener, OnItemClickListener {

    override fun getViewModelIndex(): Int = 1

    val mAdapter: BaseQuickAdapter<T, *> by lazy {
         createAdapter()
     }

    abstract fun createAdapter(): BaseQuickAdapter<T, *>
    abstract fun getRequestViewModel(): BaseListViewModel<T>

    override fun initAdapter() {
        mAdapter.run {
            if (this is LoadMoreModule)
                loadMoreModule.setOnLoadMoreListener(this@BaseAdapterRcPagerFragment)
            setOnItemClickListener(this@BaseAdapterRcPagerFragment)
        }
    }

    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager(),mAdapter,getItemDecoration())
    }

    override fun getItemDecoration():RecyclerView.ItemDecoration{
        return SpacesItemDecoration(getSpanCount(),mAdapter.headerLayoutCount,dp2px(getItemSpace()))
    }

    override fun createObserver() {
        createListVmObserve(getRequestViewModel())
    }

    /**
     * 第一次加载和刷新还是有区别的，所以这里分开
     */
    override fun onFirstLoading() {
        showLoadingPage()
        getRecyclerView().scrollToPosition(0)
        getRequestViewModel().run {
            isFirst = true
            requestNewData()
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        getRequestViewModel().run{
            isFresh = true
            requestNewData()
        }
    }

    /**
     * 加载更多(page已经在 loadListData 方法里增加了，所以这里只管请求数据）
     * page的更改绝对不能放在这里处理，因为加载更多可能存在失败的情况。
     */
    override fun onLoadMore() {
        getRequestViewModel().requestServer()
    }

}