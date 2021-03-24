package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
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
class SearchResultModel : ArticleViewModel() {

    var mKey: String? = null

    override fun requestServer() {
        request({
            val response = RxHttp.postForm(Url.SEARCH, page)
                .add("k",mKey)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        })
    }

}