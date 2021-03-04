package com.theone.demo.app.net

import rxhttp.wrapper.annotation.DefaultDomain


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
 * @describe 请求接口
 * @email 625805189@qq.com
 * @remark
 */
object Url {

    @JvmField
    @DefaultDomain //设置为默认域名
    var base = "https://www.wanandroid.com/"

    /**
     * 登录
     * username,password
     */
    var LOGIN = "user/login"

    /**
     * 注册
     * username,password,repassword
     */
    var REGISTER = "user/register"

    /**
     * 登出
     */
    var LOGIN_OUT = "user/logout/json"


    /**
     * 获取个人积分
     */
    var USER_COIN = "lg/coin/userinfo/json"


    /**
     * 首页文章
     */
    var HOME_ARTICLE = "article/list/%d/json"

    /**
     * 首页Banner
     */
    var HOME_BANNER = "banner/json"

    /**
     * 项目分类
     */
    var PROJECT_ITEM = "project/tree/json"

    /**
     * 项目内容
     */
    var PROJECT_DATA = "project/list/%d/json?cid=%d"

    /**
     * 广场
     */
    var PLAZA = "user_article/list/%d/json"

    /**
     * 问答
     */
    var QA = "wenda/list/%d/json "

    /**
     * 体系
     */
    var TREE = "tree/json"

    /**
     * 体系
     */
    var TREE_DATA = "article/list/%d/json?cid=%d"

    /**
     * 导航
     */
    var NAVIGATION = "navi/json"

    /**
     * 微信公众号
     */
    var WX_GZH = "wxarticle/chapters/json"

    /**
     * 公众号历史数据
     */
    var WX_GZH_DATA = "wxarticle/list/%d/%d/json"

}