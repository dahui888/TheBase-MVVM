package com.theone.mvvm.core

import android.app.Application
import com.theone.common.ext.LogInit
import com.theone.common.ext.currentProcessName
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.core.ext.initLoadSir

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

    override fun init(application: Application) {
        super.init(application)
        LogInit(DEBUG)

        initLoadSir()

        // TODO 以下内容还在测试...
//        val processName = currentProcessName
//        if (processName == packageName) {
//            // 主进程初始化
//            onMainProcessInit()
//        } else {
//
//            // 其他进程初始化
//            processName?.let { onOtherProcessInit(it) }
//        }
    }

    open fun onMainProcessInit() {

    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    open fun onOtherProcessInit(processName: String) {}

}