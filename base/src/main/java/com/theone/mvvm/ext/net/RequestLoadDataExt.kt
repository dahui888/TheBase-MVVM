package com.theone.mvvm.ext.net

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.ext.showEmpty
import com.theone.mvvm.ext.showError
import com.theone.mvvm.ext.util.logE
import com.theone.mvvm.net.IPageInfo
import com.theone.mvvm.util.ToastUtil


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
 * @date 2021/2/22 0022
 * @describe QMUI相关组件的一些扩展函数
 * @email 625805189@qq.com
 * @remark
 */

fun <T> loadListData(
    vm: BaseListViewModel<T>,
    adapter: BaseQuickAdapter<T, *>,
    loader: LoadService<Any>
) {
    val list = vm.getResponse().value
    val isNewData = vm.mPage.value == vm.mFirstPage.value
    if (list.isNullOrEmpty()) {
        if (isNewData) {
            loader.showEmpty()
        } else {
            adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
        }
        return
    }
    if (isNewData) {
        vm.isFirstLoad.postValue(false)
        vm.isHeadFresh.postValue(false)
        adapter.setList(list)
        loader.showSuccess()
    } else {
        adapter.addData(list)
    }
    val pageInfo = vm.mPageInfo.value
    if (pageInfo == null || pageInfo.getPageTotalCount() > pageInfo.getPage()) {
        vm.mPage.value++
        adapter.loadMoreModule.loadMoreComplete()
    } else {
        adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
    }
}

fun <T> loadListError(
    errorMsg: String,
    vm: BaseListViewModel<T>,
    adapter: BaseQuickAdapter<T, *>,
    loader: LoadService<Any>
) {
    when {
        vm.isFirstLoad.value -> {
            loader.showError(errorMsg)
        }
        vm.isHeadFresh.value -> {
            ToastUtil.show(errorMsg)
        }
        else -> {
            adapter.loadMoreModule.loadMoreFail()
        }
    }
}
