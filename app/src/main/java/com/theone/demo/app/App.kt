package com.theone.demo.app

import com.tencent.mmkv.MMKV
import com.theone.common.ext.LogInit
import com.theone.demo.BuildConfig
import com.theone.demo.app.util.RxHttpManager
import com.theone.mvvm.core.CoreApplication
import rxhttp.wrapper.param.RxHttp


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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class App : CoreApplication() {

    override fun onCreate() {
        super.onCreate()
        LogInit(BuildConfig.DEBUG)
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        RxHttp.init(
            RxHttpManager.getHttpClient(
                RxHttpManager.HttpBuilder().setNeedCookie(true)),BuildConfig.DEBUG)
    }

}