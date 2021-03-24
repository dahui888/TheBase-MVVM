package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.util.CacheUtil
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.viewmodel.BaseListViewModel


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
 * @date 2021/3/11 0011
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SearchViewModel : BaseListViewModel<String>() {

    override fun requestServer() {
        request({
            val res = CacheUtil.getSearchHistoryData()
            onSuccess(
                res,
                PagerResponse<String>().apply {
                    curPage = 1
                    pageCount = 1
                })
        })

    }

}