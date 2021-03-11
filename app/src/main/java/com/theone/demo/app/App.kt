package com.theone.demo.app

import com.tencent.mmkv.MMKV
import com.theone.demo.BuildConfig
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.util.RxHttpManager
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
class App : BaseApplication() {

    override fun isDebug(): Boolean = true

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        RxHttp.init(RxHttpManager.getHttpClient(RxHttpManager.HttpBuilder().setNeedCookie(true)),true)
    }

}