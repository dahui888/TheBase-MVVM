package com.theone.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.base.ext.getVmClazz


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
abstract class BaseVmFragment<VM : BaseViewModel> : BaseFragment() {

    lateinit var mVm: VM

    protected open fun getViewModelIndex():Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mVm = createViewModel()
        super.onViewCreated(view, savedInstanceState)
        createObserver()
        initData()
    }

    /**
     * 创建viewModel
     */
    open fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this,getViewModelIndex()))
    }

    abstract fun createObserver()
    abstract fun initData()

}