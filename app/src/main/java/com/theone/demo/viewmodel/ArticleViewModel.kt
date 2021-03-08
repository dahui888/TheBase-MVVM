package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.CollectBus
import com.theone.mvvm.base.ext.request
import com.theone.mvvm.callback.livedata.StringLiveData
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

abstract class ArticleViewModel(val url: String? = null) : BaseDemoViewModel<ArticleResponse>() {

    //收藏文章
    private val collectionError: StringLiveData = StringLiveData()

    fun getCollectionError():StringLiveData = collectionError

    override fun requestServer() {
        request({
            val response = RxHttp.get(url, getPage())
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        })
    }

    fun collection(article: ArticleResponse,event:EventViewModel) {
        val url = if(article.collect) Url.UN_LIST_COLLECTION else Url.COLLECTION_ARTICLE
        request({
            RxHttp.postForm(url, article.id)
                .toResponse<String>()
                .await()
            event.collectEvent.value = CollectBus(article.id, !article.collect)
        },null,collectionError)
    }

}