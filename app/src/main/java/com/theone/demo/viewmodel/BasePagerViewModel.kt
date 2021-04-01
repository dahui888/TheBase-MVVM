package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
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
abstract class BasePagerViewModel<T> : BaseListViewModel<T>() {

    init {
        startPage = 0
    }

    open fun onSuccess(response: PagerResponse<List<T>>?) {
        onSuccess(response?.datas, response)
    }

    open fun getCacheMode(): CacheMode {
        return when {
            isFirst -> CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK
            isFresh -> CacheMode.NETWORK_SUCCESS_WRITE_CACHE
            else -> CacheMode.ONLY_NETWORK
        }
    }

}

fun getCacheMode(isFirst: Boolean): CacheMode {
    return if (isFirst)
        CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK
    else
        CacheMode.NETWORK_SUCCESS_WRITE_CACHE
}

