package com.theone.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.base.ext.getVmClazz
import com.theone.mvvm.base.ext.loadSirInit
import com.theone.mvvm.base.ext.qmui.hideLoadingDialog
import com.theone.mvvm.base.ext.qmui.showLoadingDialog
import com.theone.mvvm.base.ext.updateStatusBarMode
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.base.ext.util.logI


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

    protected open fun getViewModelIndex(): Int = 0

    override fun onViewCreated(rootView: View) {
        mVm = createViewModel()
        super.onViewCreated(rootView)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        addLoadingObserve(mVm)
        createObserver()
    }

    /**
     * 创建viewModel
     */
    open fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this, getViewModelIndex()))
    }

    abstract fun initData()
    abstract fun createObserver()

    protected open fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInFragment(this) {
                showLoadingDialog(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInFragment(this) {
                hideLoadingDialog()
            }
        }
    }

}