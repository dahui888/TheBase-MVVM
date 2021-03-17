package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.mvvm.base.ext.request
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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class RankViewModel:BasePagerViewModel<IntegralResponse>() {

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.COIN_RANK,getPage())
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<IntegralResponse>>>()
                .await()
            onSuccess(response)
        })
    }

}