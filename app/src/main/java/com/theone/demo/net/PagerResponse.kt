package com.theone.demo.net

import com.theone.mvvm.net.IPageInfo

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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class PagerResponse<T> : IPageInfo {
    private val curPage = 0
    private val pageCount = 0
    private val size = 0
    private val total = 0
    val datas: T? = null

    override fun getPage(): Int {
        return curPage
    }

    override fun getPageTotalCount(): Int {
        return pageCount
    }

    override fun getTotalCount(): Int {
        return total
    }

    override fun getPageSize(): Int {
        return size
    }
}