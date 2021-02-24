package com.theone.mvvm.ext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.theone.mvvm.base.viewmodel.BaseListViewModel
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

fun <T> loadListData(vm: BaseListViewModel<T>,adapter: BaseQuickAdapter<T,*>,loader: LoadService<Any>) {
    val response = vm.getResponse().value!!
    if(response.isSuccess()){
        val isNewData = vm.mPage.value == 1
        if (response.isEmpty()){
            if(isNewData){
                loader.showEmpty()
            }else{
                adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
            }
            return
        }
        val list = response.getData()
        val pageInfo = response.getPageInfo()
        if (isNewData) {
            vm.isFirstLoad.postValue(false)
            vm.isHeadFresh.postValue(false)
            adapter.setList(list)
            loader.showSuccess()
        } else {
            adapter.addData(list!!.toMutableList())
        }
        if(pageInfo == null || pageInfo.getPageCount()>pageInfo.getPage()){
            vm.mPage.value++
            adapter.loadMoreModule.loadMoreComplete()
        } else {
            adapter.loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
        }
    }else{
        when {
            vm.isFirstLoad.value -> {
                loader.showError(response.getMsg()!!)
            }
            vm.isHeadFresh.value -> {
                ToastUtil.show(response.getMsg()!!)
            }
            else -> {
                adapter.loadMoreModule.loadMoreFail()
            }
        }
    }

}
