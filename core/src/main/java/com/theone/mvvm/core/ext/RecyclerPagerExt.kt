package com.theone.mvvm.core.ext

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.common.ext.logE
import com.theone.common.ext.logI
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.base.fragment.BasePagerAdapterFragment
import com.theone.mvvm.core.base.fragment.IRecyclerPager
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.widge.loadsir.core.LoadService

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
 * @date 2021-03-25 09:38
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun RecyclerView.init(
    manager: RecyclerView.LayoutManager,
    mAdapter: BaseQuickAdapter<*, *>? = null,
    decoration: RecyclerView.ItemDecoration? = null
) {
    layoutManager = manager
    mAdapter?.let {
        adapter = it
    }
    decoration?.let {
        addItemDecoration(it)
    }
}

fun Context.createLayoutManager(
    type: LayoutManagerType = LayoutManagerType.LIST,
    spanCount: Int = 2
): RecyclerView.LayoutManager {
    val layoutManager: RecyclerView.LayoutManager
    layoutManager = when (type) {
        LayoutManagerType.GRID -> GridLayoutManager(this, spanCount)
        LayoutManagerType.STAGGERED -> {
            val m = StaggeredGridLayoutManager(
                spanCount,
                StaggeredGridLayoutManager.VERTICAL
            )
            m.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            return m
        }
        else -> LinearLayoutManager(this)
    }
    return layoutManager
}

fun <T, VM : BaseListViewModel<T>> BasePagerAdapterFragment<T, VM, *>.createListVmObserve() {
    mViewModel.run {
        getResponseLiveData().observeInFragment(this@createListVmObserve) {
            loadListData(this)
        }
        getErrorMsgLiveData().observeInFragment(this@createListVmObserve, Observer {
            loadListError(this)
        })
    }
}


/**
 * List数据请求成功后设置数据的统一封装
 * @param vm        数据源
 * @param adapter   适配器
 * @param loader    界面管理器
 */
fun <T> IRecyclerPager<T>.loadListData(
    vm: BaseListViewModel<T>
) {
    val list = vm.getResponseLiveData().value
    val isNewData = vm.page == vm.startPage
    if (list.isNullOrEmpty()) {
        if (isNewData) {
            onEmptyData()
        } else {
            onLoadMoreEnd()
        }
        return
    }
    if (isNewData) {
        if (vm.isFirst) {
            vm.isFirst = false
            onFirstLoadSuccess(list)
        } else {
            vm.isFresh = false
            onRefreshSuccess(list)
        }
    } else {
        onLoadMoreSuccess(list)
    }
    val pageInfo = vm.pageInfo
//    "onSuccess loadListData".logE()
//    pageInfo?.run {
//        "${getPage()}  ${getPageTotalCount()} ${getTotalCount()}".logI()
//    }
    if (pageInfo == null || pageInfo.getPageTotalCount() > pageInfo.getPage()) {
        vm.page++
        onLoadMoreComplete()
    } else {
        onLoadMoreEnd()
    }
}

/**
 * 请求失败时
 * @param vm        数据源
 * @param adapter   适配器
 * @param loader    界面管理器
 */
fun <T> IRecyclerPager<T>.loadListError(
    vm: BaseListViewModel<T>
) {
    with(vm.getErrorMsgLiveData().value) {
        when {
            vm.isFirst -> {
                onFirstLoadError(this)
            }
            vm.isFresh -> {
                onRefreshError(this)
            }
            else -> {
                onLoadMoreError(this)
            }
        }
    }
}


