package com.theone.demo.viewmodel

import com.theone.demo.app.net.PagerResponse
import com.theone.demo.app.util.CacheUtil
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.base.viewmodel.BaseViewModel


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

    init {
        // 不显示空界面，数据为空，也要显示内容
        showEmpty.value = false
    }

    override fun requestServer() {
        onSuccess(
            CacheUtil.getSearchHistoryData(),
            PagerResponse<String>().apply {
            curPage = 1
            pageCount = 1
        })
    }

}