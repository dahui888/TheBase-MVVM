package com.theone.demo.app.util

import com.theone.demo.app.net.Url
import com.theone.mvvm.base.ext.util.logE
import okhttp3.Cookie
import okhttp3.HttpUrl
import rxhttp.HttpSender
import rxhttp.wrapper.cookie.ICookieJar


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
 * @date 2021/3/5 0005
 * @describe Cookie相关工具
 * @email 625805189@qq.com
 * @remark Wanandroid登录后会返回Cookie,请求时根据Cookie判断用户。
 *         Cookie管理RxHttp提供{ https://github.com/liujingxing/okhttp-RxHttp/wiki/Cookie%E7%AE%A1%E7%90%86 }
 */

object CookieUtil {

    /**
     * 是否存在Cookie
     */
    fun isExistCookie(): Boolean {
        getCacheCookie().toString().logE()
        return !getCacheCookie().isNullOrEmpty()
    }

    fun setCookie() {
        getCookieJar().saveCookie(getHttpUrl(), getCacheCookie())
    }

    /**
     * 获取Cookie
     */
    private fun getCacheCookie(): List<Cookie>? {
        return getCookieJar().loadCookie(getHttpUrl())
    }

    /**
     * 移除所有Cookie
     */
    fun removeAllCookie() {
        getCookieJar().removeAllCookie()
    }

    private fun getHttpUrl(): HttpUrl? = HttpUrl.parse(Url.BASE_URL)

    private fun getCookieJar(): ICookieJar = HttpSender.getOkHttpClient().cookieJar() as ICookieJar

}

