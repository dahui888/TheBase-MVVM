package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.IntegralHistoryResponse
import com.theone.mvvm.core.ext.request
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse


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
 * @date 2021/3/17 0017
 * @describe 积分记录
 * @email 625805189@qq.com
 * @remark
 */
class IntegralHistoryViewModel:BasePagerViewModel<IntegralHistoryResponse>() {

    init {
        startPage = 1
    }

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.INTEGRAL_HISTORY,page)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<IntegralHistoryResponse>>>()
                .await()
            onSuccess(response)
        })
    }

}