package com.theone.demo.net

import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain


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
 * @date 2021/2/18 0018
 * @describe 接口
 * @email 625805189@qq.com
 * @remark
 */
object Url {

    @JvmField
    @DefaultDomain //设置为默认域名
    var base = "https://www.wanandroid.com/"

    var HOME_ARTICLE = "article/list/%d/json"

    var HOME_BANNER = "banner/json"

    var PROJECT = "project/tree/json"

    var PROJECT_DATA = "project/list/%d/json?cid=%d"

}