package com.theone.demo

import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.util.RxHttpManager
import com.theone.mvvm.widge.loadCallBack.EmptyCallback
import com.theone.mvvm.widge.loadCallBack.ErrorCallback
import com.theone.mvvm.widge.loadCallBack.LoadingCallback
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

    override fun onCreate() {
        super.onCreate()
        RxHttp.init(RxHttpManager.getHttpClient(RxHttpManager.HttpBuilder()),true)
    }

}