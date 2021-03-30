package com.theone.mvvm.core.fragment

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

    //界面状态管理者
    var mLoadSir: LoadService<Any>?=null

    override fun onViewCreated(rootView: View) {
        mLoadSir = loadSirInit(mContent) {
            onErrorPageClick()
        }
        super.onViewCreated(rootView)
    }

    protected open fun onErrorPageClick() {}

}