package com.theone.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.getVmClazz
import com.theone.mvvm.ext.qmui.hideLoadingDialog
import com.theone.mvvm.ext.qmui.showLoadingDialog


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
abstract class BaseVmFragment<VM : BaseViewModel> : BaseQMUIFragment() {

    lateinit var mViewModel: VM

    /**
     * ViewModel的位置
     */
    protected open fun getViewModelIndex(): Int = 0

    override fun onViewCreated(rootView: View) {
        mViewModel = createViewModel()
        super.onViewCreated(rootView)
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLoadingObserve(mViewModel)
        createObserver()
    }

    /**
     * 创建viewModel
     */
    open fun createViewModel(): VM {
        return ViewModelProvider(this).get(
            getVmClazz(
                this,
                getViewModelIndex()
            )
        )
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