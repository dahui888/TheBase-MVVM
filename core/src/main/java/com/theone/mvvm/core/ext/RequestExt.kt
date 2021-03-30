package com.theone.mvvm.core.ext

import android.content.Context
import androidx.lifecycle.rxLifeScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.mvvm.base.appContext
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.core.viewmodel.BaseListViewModel
import com.theone.mvvm.core.viewmodel.BaseRequestViewModel
import com.theone.mvvm.core.widge.loadsir.core.LoadService
import com.theone.mvvm.ext.qmui.showMsgDialog
import com.theone.mvvm.ext.qmui.showMsgTipsDialog
import kotlinx.coroutines.CoroutineScope


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

/**
 * 请求
 * @param block 请求的主函数体，得到数据后调用onSuccess方法
 * @param loadingMsg 请求时的提示语句，不为空时才开启弹窗提示
 * @param errorLiveData 接收错误的LiveData
 */
fun <T> BaseRequestViewModel<T>.request(
    block: suspend CoroutineScope.() -> Unit,
    loadingMsg: String? = null,
    errorLiveData: StringLiveData? = null
) {
    rxLifeScope.launch({
        block()
    }, {
        onError(it, errorLiveData)
    }, {
        loadingMsg?.let {
            loadingChange.showDialog.value = loadingMsg
        }
    }, {
        onFinally()
        loadingMsg?.let {
            loadingChange.dismissDialog.value = false
        }
    }
    )
}

/**
 * List数据请求成功后设置数据的统一封装
 * @param vm        数据源
 * @param adapter   适配器
 * @param loader    界面管理器
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
        if (vm.isFirst) {
            vm.isFirst = false
            vm.getFirstLoadSuccessLiveData().value = true
            loader?.showSuccess()
        } else {
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

/**
 * 请求失败时
 * @param vm        数据源
 * @param adapter   适配器
 * @param loader    界面管理器
 */
fun <T> loadListError(
    context:Context?,
    vm: BaseListViewModel<T>,
    adapter: BaseQuickAdapter<T, *>,
    loader: LoadService<Any>?
) {
    val errorMsg = vm.getErrorMsgLiveData().value
    when {
        vm.isFirst -> {
            loader?.showError(errorMsg)
        }
        vm.isFresh -> {
            context?.showMsgTipsDialog(errorMsg)
        }
        else -> {
            adapter.loadMoreModule.loadMoreFail()
        }
    }
}
