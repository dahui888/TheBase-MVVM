package com.theone.mvvm.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.mvvm.core.net.IPageInfo


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
 * @describe 列表类型数据请求
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseListViewModel<T> : BaseRequestViewModel<List<T>>() {

    // 分页信息,实体需实现 IPageInfo
    private val pageInfo: MutableLiveData<IPageInfo> = MutableLiveData()
    // 是否第一次加载
    var isFirst: Boolean = true
    // 是否刷新
    var isFresh: Boolean = false
    // 开始的页数
     var startPage: Int = 1
    // 当前页面
    var page: Int = startPage

    fun getPageInfoLiveData():LiveData<IPageInfo> = pageInfo

    /**
     *     数据请求成功后调用此方法
     * @param response 返回的数据
     * @param pageInfo 页码信息
     */
    open fun onSuccess( response:List<T>?, pageInfo: IPageInfo? ){
        onSuccess(response)
        this.pageInfo.value = pageInfo
    }

    /**
     * 供外部调用的请求最新的数据
     */
    fun requestNewData(){
        page = startPage
        requestServer()
    }

}