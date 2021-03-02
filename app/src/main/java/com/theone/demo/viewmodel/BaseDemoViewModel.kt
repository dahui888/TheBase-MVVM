package com.theone.demo.viewmodel

import com.theone.demo.net.PagerResponse
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import rxhttp.wrapper.cahce.CacheMode


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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseDemoViewModel<T> : BaseListViewModel<T>() {

    init {
        space.value = 12
        mFirstPage.value = 0
    }

    fun onSuccess(response: PagerResponse<List<T>>?) {
        super.onSuccess(response?.datas,response)
    }

    fun getCacheMode(): CacheMode {
        return if (isFirstLoad.value)
            CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK
        else
            CacheMode.ONLY_NETWORK
    }

}

fun getCacheMode(isFirst: Boolean): CacheMode{
    return if (isFirst)
        CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK
    else
        CacheMode.ONLY_NETWORK
}

