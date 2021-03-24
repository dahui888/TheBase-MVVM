package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.ArticleResponse
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
 * @date 2021/3/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class WxGzhItemViewModel : ArticleViewModel() {

    var mId: Int = 0

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.WX_GZH_DATA,mId,page)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        })
    }

}