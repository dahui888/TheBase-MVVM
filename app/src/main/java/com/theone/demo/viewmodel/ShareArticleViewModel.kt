package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.ShareResponse
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
 * @date 2021/3/5 0005
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ShareArticleViewModel:ArticleViewModel() {

    init {
        mFirstPage.value = 1
    }

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.MY_SHARE_ARTICLE,getPage())
                .setCacheMode(getCacheMode())
                .toResponse<ShareResponse>()
                .await()
            onSuccess(response?.shareArticles)
        })
    }

}