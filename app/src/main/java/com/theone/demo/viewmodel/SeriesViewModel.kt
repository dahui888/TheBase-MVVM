package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.demo.entity.Brand
import com.theone.demo.entity.Series
import com.theone.demo.net.PageInfo
import com.theone.demo.net.Response
import com.theone.demo.net.Url
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import rxhttp.toClass
import rxhttp.wrapper.cahce.CacheMode
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
 * @date 2021/2/25 0025
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SeriesViewModel: BaseDemoViewModel<Series>() {

    var mBrandId :String = String()

    override fun requestServer() {
        rxLifeScope.launch({
            val response = RxHttp.get(Url.SERIES)
                .add("brandid",mBrandId)
                .setCacheMode(getCacheMode())
                .toClass<Response<List<Series>>>()
                .await()
            onSuccess(response)
        }, {
            onError(it)
        })
    }

}