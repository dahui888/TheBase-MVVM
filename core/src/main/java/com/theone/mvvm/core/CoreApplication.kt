package com.theone.mvvm.core

import com.qmuiteam.qmui.util.QMUIResHelper
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.core.ext.initLoadSir
import com.theone.mvvm.core.widge.loadsir.callback.ErrorCallback
import com.theone.mvvm.core.widge.loadsir.callback.LoadingCallback
import com.theone.mvvm.core.widge.loadsir.callback.SuccessCallback
import com.theone.mvvm.core.widge.loadsir.core.LoadSir

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
 * @date 2021/3/23 0022
 * @describe 提供默认
 * @email 625805189@qq.com
 * @remark
 */
abstract class CoreApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initLoadSir()
    }

}