package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.entity.Brand
import com.theone.demo.entity.Series
import com.theone.demo.net.PageInfo
import com.theone.demo.net.Response
import com.theone.demo.net.Url
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.net.IResponse
import rxhttp.toClass
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.param.RxHttp


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
    }

    fun onSuccess(response: Response<List<T>>) {
        response.pageInfo = PageInfo(1, 1, 1, 1)
        super.onSuccess(response)
    }

    fun getCacheMode(): CacheMode {
        return if (isFirstLoad.value)
            CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK
        else
            CacheMode.ONLY_NETWORK
    }

}

