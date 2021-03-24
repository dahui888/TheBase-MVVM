package com.theone.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.rxLifeScope
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.net.Url
import com.theone.mvvm.callback.livedata.StringLiveData
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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class HomeViewModel : ArticleViewModel() {

    private val mBanners: UnPeekLiveData<List<BannerResponse>> = UnPeekLiveData()
    fun getBanners(): ProtectedUnPeekLiveData<List<BannerResponse>> = mBanners

    override fun requestServer() {
        request({
            val response = RxHttp.get(Url.HOME_ARTICLE, page)
                .setCacheMode(getCacheMode())
                .toResponse<PagerResponse<List<ArticleResponse>>>()
                .await()
            if(isFirst || isFresh){
                mBanners.value = RxHttp.get(Url.HOME_BANNER)
                    .setCacheMode(getCacheMode())
                    .toResponse<List<BannerResponse>>()
                    .await()
            }
            onSuccess(response)
        })
    }

}

