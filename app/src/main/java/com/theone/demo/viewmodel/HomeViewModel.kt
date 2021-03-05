package com.theone.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.rxLifeScope
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class HomeViewModel : ArticleViewModel() {

    private val mBanners: MutableLiveData<List<BannerResponse>> = MutableLiveData()

    fun getBanners(): MutableLiveData<List<BannerResponse>> = mBanners

    init {
        space.value = 0
    }

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.HOME_ARTICLE, mPage.value)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()

            onSuccess(response)
        })
    }

    fun requestBanner() {
        rxLifeScope.launch({
            val banners = RxHttp.get(Url.HOME_BANNER)
                .setCacheMode(getCacheMode())
                .toResponse<List<BannerResponse>>()
                .await()
            mBanners.postValue(banners)
        }, {
        })
    }

}

