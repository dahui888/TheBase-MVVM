package com.theone.mvvm.core.base.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.ext.loadSirInit
import com.theone.mvvm.core.widge.loadsir.core.LoadService

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
 * @date 2021-04-16 15:19
 * @describe BaseCoreActivity
 * @email 625805189@qq.com
 * @remark 添加界面状态管理
 */
abstract class BaseCoreActivity<VM : BaseViewModel, DB : ViewDataBinding>:BaseVmDbActivity<VM,DB>() {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    override fun setContentView(view: View?) {
        super.setContentView(view)
        mLoadSir = loadSirInit(getContentView()) {
            onPageReLoad()
        }
    }

    /**
     * 错误、空界面重新
     */
    protected open fun onPageReLoad() {}

    override fun initData() {}


}