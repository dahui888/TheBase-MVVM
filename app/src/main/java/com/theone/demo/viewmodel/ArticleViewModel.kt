package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.app.net.PagerResponse
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

abstract class ArticleViewModel(val url:String):BaseDemoViewModel<ArticleResponse>() {

    override fun requestServer() {
        rxLifeScope.launch({
            val response = RxHttp.get(url,getPage())
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        }, {
            onError(it)
        })
    }

}