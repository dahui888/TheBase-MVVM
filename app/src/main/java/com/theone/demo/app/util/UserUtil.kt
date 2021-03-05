package com.theone.demo.app.util

import android.content.Intent
import com.google.gson.Gson
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.ui.activity.LoginActivity


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
        CookieUtil.removeAllCookie()
        setUser(null)
    }

    fun setUser(userInfo: UserInfo?) {
        val exits = null != userInfo
        val user = if (exits) Gson().toJson(userInfo) else ""
        MMKVUtil.putString(USER, user)
    }

    fun getUser(): UserInfo? {
        val userStr = MMKVUtil.getString(USER)
        return if (userStr.isNullOrEmpty() || !CookieUtil.isExistCookie())
            null
        else
            Gson().fromJson(userStr, UserInfo::class.java)

    }


}


