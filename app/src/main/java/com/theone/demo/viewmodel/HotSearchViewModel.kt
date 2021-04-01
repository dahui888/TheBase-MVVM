package com.theone.demo.viewmodel

import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.SearchResponse
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.databind.BooleanObservableField
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

     val isFirst:BooleanObservableField = BooleanObservableField(true)

    override fun requestServer() {
        request({
           val res =  RxHttp.get(Url.HOT_KEYS)
                .setCacheMode(getCacheMode(isFirst.get()))
                .toResponse<List<SearchResponse>>()
               .await()
            onSuccess(res)
        })
    }

}