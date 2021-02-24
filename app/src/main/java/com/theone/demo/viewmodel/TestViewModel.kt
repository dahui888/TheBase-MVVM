package com.theone.demo.viewmodel

import androidx.lifecycle.rxLifeScope
import com.theone.demo.entity.Brand
import com.theone.demo.net.PageInfo
import com.theone.demo.net.Response
import com.theone.demo.net.Url
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.ext.util.logE
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp


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
class TestViewModel : BaseListViewModel<Brand>() {

    init {
        space.value = 12
    }

    override fun requestServer() {
        rxLifeScope.launch {
            val response = RxHttp.get(Url.BRAND)
                .toClass<Response<List<Brand>>>()
                .await()
            response.pageInfo = PageInfo(1, 1, 1, 1)
            response.error_code = 1
            getResponse().postValue(response)
        }
    }

}