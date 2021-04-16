package com.theone.mvvm.base.activity

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
 * @date 2021-03-31 15:01
 * @describe 持有 ViewModel Activity基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseQMUIActivity(), IBaseViewModel<VM> {

     val mViewModel: VM by lazy {
        createViewModel()
    }

    /**
     * ViewModel的位置
     */
    override fun getViewModelIndex(): Int = 0

    /**
     * 创建viewModel
     */
    override fun createViewModel(): VM = ViewModelProvider(this).get(
        getVmClazz(
            this,
            getViewModelIndex()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建观察者
        createObserver()
    }

    /**
     * 添加加载观察
     * @param viewModels Array<out BaseViewModel>
     */
    override fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInActivity(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInActivity(this) {
                hideLoading()
            }
        }
    }

}