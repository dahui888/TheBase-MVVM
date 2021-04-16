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
 * @describe ViewModel基类相关
 * @email 625805189@qq.com
 * @remark
 */
interface IBaseViewModel<VM:BaseViewModel> {

    /**
     * ViewMode在泛型中的的位置
     */
    fun getViewModelIndex(): Int

    /**
     * 创建ViewModel
     * @return VM
     */
    fun createViewModel(): VM

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 创建观察者
     */
    fun createObserver()

    /**
     * BaseViewModel添加Loading观察
     * @param viewModels Array<out BaseViewModel>
     */
    fun addLoadingObserve(vararg viewModels: BaseViewModel)

}
