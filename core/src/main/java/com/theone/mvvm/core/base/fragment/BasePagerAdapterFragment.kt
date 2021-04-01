package com.theone.mvvm.core.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.LoadMoreModule
import com.theone.common.ext.dp2px
import com.theone.mvvm.core.ext.*
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.widge.TheSpaceItemDecoration


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
 * @describe 给定了默认的Adapter初始化和RecyclerView初始化, 需要自定义下拉刷新控件继承此类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BasePagerAdapterFragment
<T, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BasePagerRecyclerViewFragment<VM, DB>()
    , OnLoadMoreListener, OnItemClickListener {

    /**
     * 由于这个界面需要添加一个泛型T,所以要改变一下ViewModel的位置
     * @return Int
     */
    override fun getViewModelIndex(): Int = 1

    val mAdapter: BaseQuickAdapter<T, *> by lazy {
        createAdapter()
    }

    abstract fun createAdapter(): BaseQuickAdapter<T, *>

    /**
     * 是否不显示<没有更多数据了>
     * @return Boolean
     */
    fun goneLoadMoreEndView(): Boolean = false

    /**
     * 初始化默认的适配器配置
     * 如有需要，重写此方法进行更改
     */
    override fun initAdapter() {
        mAdapter.run {
            if (this is LoadMoreModule)
                loadMoreModule.setOnLoadMoreListener(this@BasePagerAdapterFragment)
            setOnItemClickListener(this@BasePagerAdapterFragment)
        }
    }

    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager(), mAdapter, getItemDecoration())
    }

    /**
     * 给一个默认的间距配置
     * @return RecyclerView.ItemDecoration
     */
    override fun getItemDecoration(): RecyclerView.ItemDecoration {
        return TheSpaceItemDecoration(
            getSpanCount(),
            dp2px(getItemSpace()),
            mAdapter.headerLayoutCount
        )
    }

    override fun createObserver() {
        createListVmObserve()
    }

    /**
     * 第一次加载和刷新还是有区别的，所以这里分开
     */
    override fun onFirstLoading() {
        showLoadingPage()
        // 清空数据，因为这个方法可能会在有数据时再次调用
        mAdapter.setNewInstance(null)
        // 一开始是使用这个方法，但是觉得还是直接清空数据比较好
        //getRecyclerView().scrollToPosition(0)
        mViewModel.run {
            isFirst = true
            requestNewData()
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        mViewModel.run {
            isFresh = true
            requestNewData()
        }
    }

    /**
     * 加载更多(page已经在 loadListData 方法里增加了，所以这里只管请求数据）
     * page的更改绝对不能放在这里处理，因为加载更多可能存在失败的情况。
     */
    override fun onLoadMore() {
        mViewModel.requestServer()
    }

}