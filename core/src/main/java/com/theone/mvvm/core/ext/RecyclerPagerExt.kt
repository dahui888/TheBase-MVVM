package com.theone.mvvm.core.ext

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.fragment.BaseAdapterRcPagerFragment
import com.theone.mvvm.core.viewmodel.BaseListViewModel
import com.theone.mvvm.core.widge.SpacesItemDecoration
import com.theone.mvvm.ext.util.dp2px

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

fun <T> BaseAdapterRcPagerFragment<T, *, *>.createListVmObserve(vm: BaseListViewModel<T>) {
    vm.run {
        getResponseLiveData().observeInFragment(this@createListVmObserve, Observer {
            loadListData(
                vm,
                mAdapter,
                mLoadSir
            )
        })
        getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
            loadListError(
                it,
                vm,
                mAdapter,
                mLoadSir
            )
        })
        getFinallyLiveData().observe(viewLifecycleOwner, Observer {
            onRefreshComplete()
        })
    }
}
