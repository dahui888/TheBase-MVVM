package com.theone.mvvm.ext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.mvvm.base.viewmodel.BaseListViewModel


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

fun <T> BaseQuickAdapter<T, *>.loadListData(vm: BaseListViewModel<T>) {
    val response = vm.getResponse().value
    val list = response!!.getData()
    if (null == list ||list.isEmpty()){
        return
    }
    val data = list.toMutableList()
    if (vm.mPage == 1) {
        setList(data)
    } else {
        addData(data)
    }
    val pageInfo = response.getPageInfo()
    if(pageInfo == null || pageInfo.getPageCount()>pageInfo.getPage()){
        vm.mPage++
        loadMoreModule.loadMoreComplete()
    } else {
        loadMoreModule.loadMoreEnd(vm.goneLoadMoreEndView)
    }
}
