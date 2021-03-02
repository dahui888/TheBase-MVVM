package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.net.Url
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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ProjectViewModel : BaseRequestViewModel<List<ClassifyResponse>>() {

    override fun requestServer() {
        rxLifeScope.launch({
            val response = RxHttp.get(Url.PROJECT)
                .setCacheMode(getCacheMode(true))
                .toResponse<List<ClassifyResponse>>()
                .await()
            onSuccess(response)
        }, {
            onError(it)
        })
    }

}