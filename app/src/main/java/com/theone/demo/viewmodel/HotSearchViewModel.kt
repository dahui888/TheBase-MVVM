package com.theone.demo.viewmodel

import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.SearchResponse
import com.theone.mvvm.base.ext.request
import com.theone.mvvm.base.viewmodel.BaseRequestViewModel
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
 * @date 2021/3/11 0011
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class HotSearchViewModel:BaseRequestViewModel<List<SearchResponse>>() {

    override fun requestServer() {
        request({
           val res =  RxHttp.get(Url.HOT_KEYS)
                .setCacheMode(getCacheMode(true))
                .toResponse<List<SearchResponse>>()
               .await()
            onSuccess(res)
        })
    }

}