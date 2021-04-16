package com.theone.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.theone.mvvm.base.IBaseViewModel
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
 * @describe 持有 ViewModel Fragment基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmFragment<VM : BaseViewModel> : BaseQMUIFragment(), IBaseViewModel<VM> {

    val  mViewModel: VM by lazy {
        createViewModel()
    }

    /**
     * ViewModel的位置
     */
    override fun getViewModelIndex(): Int = 0

    /**
     * QMUIFragment这个方法只会触发一次,所以将初始化放在这个方法里
     * 子类切勿乱重写这个方法
     */
    override fun onViewCreated(rootView: View) {
        super.onViewCreated(rootView)
        initData()
    }

    /**
     * observe 一定要放在这个这个方法里
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLoadingObserve(mViewModel)
        createObserver()
    }

    /**
     * 创建viewModel
     */
    override fun createViewModel(): VM = ViewModelProvider(this).get(
        getVmClazz(
            this,
            getViewModelIndex()
        )
    )

    /**
     * 添加加载观察
     * @param viewModels Array<out BaseViewModel>
     */
    override fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInFragment(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInFragment(this) {
                hideLoading()
            }
        }
    }

}