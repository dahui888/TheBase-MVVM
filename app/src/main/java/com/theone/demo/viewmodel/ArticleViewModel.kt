package com.theone.demo.viewmodel

import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.data.model.bean.CollectBus
import com.theone.mvvm.core.ext.request
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

abstract class ArticleViewModel(val url: String? = null) : BasePagerViewModel<ArticleResponse>() {

    //收藏文章
    private val collectionError: UnPeekLiveData<String> = UnPeekLiveData()

    fun getCollectionError(): ProtectedUnPeekLiveData<String> = collectionError

    override fun requestServer() {
        request({
            val response = RxHttp.get(url, page)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            onSuccess(response)
        })
    }

    override fun onSuccess(response: List<ArticleResponse>?) {
        // 第一次是从缓存里获取，这里要拿用户里收藏的判断一下
        response?.run {
            if (isFirst) {
                val user = CacheUtil.getUser()
                user?.collectIds?.forEach { id ->
                    for (index in this.indices) {
                        if(this[index].id == id.toInt()){
                            this[index].collect = true
                            break
                        }
                    }
                }
            }
        }
        super.onSuccess(response)
    }

    open fun collection(article: ArticleResponse, event: AppViewModel) {
        val url = if (article.collect) Url.UN_LIST_COLLECTION else Url.COLLECTION_ARTICLE
        request({
            RxHttp.postForm(url, article.getArticleId())
                .toResponse<String>()
                .await()
            event.collectEvent.value = CollectBus(article.getArticleId(), !article.collect)
        }, null, collectionError)
    }

}