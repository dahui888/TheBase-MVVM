package com.theone.mvvm.base

import com.theone.mvvm.base.viewmodel.BaseViewModel
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
 * @date 2021-03-31 15:04
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
interface IBaseVm<VM:BaseViewModel> {

    /**
     * ViewMode在泛型中的的位置
     */
    fun getViewModelIndex(): Int
    fun createViewModel(): VM
    fun addLoadingObserve(vararg viewModels: BaseViewModel)

    fun initData()
    fun createObserver()

}
