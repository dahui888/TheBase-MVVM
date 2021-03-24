package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.IntegralResponse
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
 * @describe 积分排行
 * @email 625805189@qq.com
 * @remark
 */
class IntegralRankViewModel:BasePagerViewModel<IntegralResponse>() {

    init {
        startPage = 1
    }

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.INTEGRAL_RANK,page)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<IntegralResponse>>>()
                .await()
            onSuccess(response)
        })
    }

}