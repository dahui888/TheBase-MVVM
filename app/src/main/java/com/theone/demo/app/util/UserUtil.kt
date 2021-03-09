package com.theone.demo.app.util

import com.google.gson.Gson
import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.util.RxHttpManager


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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

object UserUtil {

    private val USER: String = "user"

    fun isLogin(): Boolean = null != getUser()

    fun loginOut(){
        RxHttpManager.getCookieJar().clear()
        setUser(null)
    }

    fun setUser(userInfo: UserInfo?) {
        val exits = null != userInfo
        val user = if (exits) Gson().toJson(userInfo) else ""
        MMKVUtil.putString(USER, user)
    }

    fun getUser(): UserInfo? {
        val userStr = MMKVUtil.getString(USER)
        val userExist = !userStr.isNullOrEmpty()
        return if (userExist)
            Gson().fromJson(userStr, UserInfo::class.java)
        else
            null
    }


}


