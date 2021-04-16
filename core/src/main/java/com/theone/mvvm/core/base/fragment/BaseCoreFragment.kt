package com.theone.mvvm.core.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import com.theone.mvvm.core.ext.loadSirInit
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.widge.loadsir.core.LoadService

/**
 * @author The one
 * @date 2021/3/23 0022
 * @describe CoreBaseFragment
 * @email 625805189@qq.com
 * @remark 添加界面状态管理
 */
abstract class BaseCoreFragment<VM : BaseViewModel, DB : ViewDataBinding>:BaseVmDbFragment<VM,DB>() {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    /**
     *  这里拿[getContentView]进行注册
     *  无论子类需不需要TopBar,都拿content层去注册
     * @param rootView View
     */
    override fun onViewCreated(rootView: View) {
        mLoadSir = loadSirInit(getContentView()) {
            onPageReLoad()
        }
        super.onViewCreated(rootView)
    }

    /**
     * 错误、空界面重新
     */
    protected open fun onPageReLoad() {}

    /**
     * 这里把这个方法实现了，子类需要的时候重写，免的每次都要去实现这个方法
     */
    override fun onLazyInit() {}

}