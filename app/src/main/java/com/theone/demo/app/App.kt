package com.theone.demo.app

import com.tencent.mmkv.MMKV
import com.theone.demo.BuildConfig
import com.theone.demo.app.util.RxHttpManager
import com.theone.mvvm.core.CoreApplication
import com.theone.mvvm.core.ext.initLoadSir
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
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        RxHttp.init(
            RxHttpManager.getHttpClient(
                RxHttpManager.HttpBuilder().setNeedCookie(true)),true)
    }

}