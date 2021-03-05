package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.mvvm.base.ext.request
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

abstract class ArticleViewModel(val url:String? = null):BaseDemoViewModel<ArticleResponse>() {

    override fun requestServer() {
        request({
            val response = RxHttp.get(url,getPage())
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        })
    }

    fun collection(article:ArticleResponse){
        if(article.collect){

        }else{

        }
    }

}