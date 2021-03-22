package com.theone.mvvm.base.ext.net

import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.mvvm.widge.loadsir.core.LoadService
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.base.ext.showEmpty
import com.theone.mvvm.base.ext.showError
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
 * @describe
 * @email 625805189@qq.com
 * @remark
 */

fun <T> loadListData(
    vm: BaseListViewModel<T>,
    adapter: BaseQuickAdapter<T, *>,
    loader: LoadService<Any>?
) {
    val list = vm.getResponseLiveData().value
    val isNewData = vm.page == vm.startPage
    if (list.isNullOrEmpty()) {
        if (isNewData) {
            loader?.showEmpty()
        } else {
            adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
        }
        return
    }
    if (isNewData) {
        if(vm.isFirst){
            vm.isFirst = false
            vm.getFirstLoadSuccessLiveData().value = true
            loader?.showSuccess()
        }else{
            vm.isFresh = false
        }
        adapter.setList(list)
    } else {
        adapter.addData(list)
    }
    val pageInfo = vm.getPageInfoLiveData().value
    if (pageInfo == null || pageInfo.getPageTotalCount() > pageInfo.getPage()) {
        vm.page++
        adapter.loadMoreModule.loadMoreComplete()
    } else {
        adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
    }
}

fun <T> loadListError(
    errorMsg: String,
    vm: BaseListViewModel<T>,
    adapter: BaseQuickAdapter<T, *>,
    loader: LoadService<Any>?
) {
    when {
        vm.isFirst -> {
            loader?.showError(errorMsg)
        }
        vm.isFresh -> {
            ToastUtil.show(errorMsg)
        }
        else -> {
            adapter.loadMoreModule.loadMoreFail()
        }
    }
}
