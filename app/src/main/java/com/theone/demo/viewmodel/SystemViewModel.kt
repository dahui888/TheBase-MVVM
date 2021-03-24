package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.SystemResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
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
 * @date 2021/3/4 0004
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SystemViewModel:BasePagerViewModel<SystemResponse>() {

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.TREE)
                .setCacheMode(getCacheMode())
                .toResponse<List<SystemResponse>>()
                .await()
            val data = PagerResponse<List<SystemResponse>>()
            data.datas = response
            data.curPage = 1
            data.pageCount =1
            onSuccess(data)
        })
    }

}