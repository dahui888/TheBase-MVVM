package com.theone.demo.app.util

import android.content.Intent
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.ui.activity.LoginRegisterActivity
import com.theone.demo.ui.fragment.login.LoginRegisterFragment


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
fun QMUIFragment.checkLogin(isLoginAction: () -> Unit = {}) {
    if (UserUtil.isLogin()) {
        isLoginAction.invoke()
    } else {
        startFragment(LoginRegisterFragment())
//        startActivity(Intent(activity, LoginRegisterActivity::class.java))
    }
}