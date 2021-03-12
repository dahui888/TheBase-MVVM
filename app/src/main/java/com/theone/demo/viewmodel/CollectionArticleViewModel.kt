package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.CollectBus
import com.theone.mvvm.base.ext.request
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.base.net.IPageInfo
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
 * @describe 收藏的文章
 * @email 625805189@qq.com
 * @remark
 */
class CollectionArticleViewModel : ArticleViewModel(Url.MY_COLLECTION_ARTICLES) {

    // 收藏这里把所有的都设置为已收藏
    override fun onSuccess(response: List<ArticleResponse>?) {
        response?.forEach { it ->
            it.collect = true
        }
        getResponseLiveData().value = response
    }

}