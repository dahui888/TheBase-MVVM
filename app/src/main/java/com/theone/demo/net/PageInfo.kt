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
 * @date 2021/2/23 0023
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
data class PageInfo(val mPage: Int = 1, val mPageCount: Int, val mTotalCount:Int, val mPageSize:Int) :
    IPageInfo {

    override fun getPage(): Int = mPage

    override fun getPageCount(): Int = mPageCount

    override fun getTotalCount(): Int = mTotalCount

    override fun getPageSize(): Int = mPageSize

}