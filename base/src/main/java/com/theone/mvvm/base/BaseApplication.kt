package com.theone.mvvm.base

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.theone.mvvm.BuildConfig
import kotlin.properties.Delegates


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
abstract class BaseApplication : MultiDexApplication(), ViewModelStoreOwner {

    companion object {
        lateinit var INSTANCE: BaseApplication
        var DEBUG by Delegates.notNull<Boolean>()
    }

    private val mAppViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    open fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DEBUG = isDebug()
        MultiDex.install(this)
        QMUISwipeBackActivityManager.init(this)
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }


}