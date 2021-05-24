package com.theone.mvvm.core.base.fragment

import android.view.KeyEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import com.hjq.toast.ToastUtils
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.R
import com.theone.mvvm.core.callback.ICore
import com.theone.mvvm.core.ext.hideProgressDialog
import com.theone.mvvm.core.ext.registerLoadSir
import com.theone.mvvm.core.ext.showProgressDialog
import com.theone.mvvm.core.widge.loadsir.core.LoadService
import com.theone.mvvm.entity.ProgressBean

/**
 * @author The one
 * @date 2021/3/23 0022
 * @describe CoreBaseFragment
 * @email 625805189@qq.com
 * @remark 添加界面状态管理
 */
abstract class BaseCoreFragment<VM : BaseViewModel, DB : ViewDataBinding>:BaseVmDbFragment<VM,DB>(),ICore {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    override fun getLoadSir(): LoadService<Any>? = mLoadSir

    /**
     *  这里拿[getContentView]进行注册
     *  无论子类需不需要TopBar,都拿content层去注册
     * @param rootView View
     */
    override fun onViewCreated(rootView: View) {
        mLoadSir = registerLoadSir()
        super.onViewCreated(rootView)
    }

    override fun loadSirRegisterView(): View = getContentView()

    override fun showProgress(progress: ProgressBean) {
        requireActivity().showProgressDialog(progress)
    }

    override fun hideProgress() {
        hideProgressDialog()
    }

    /**
     * 这里把这些方法实现了，子类需要的时候重写，免的每次都要去实现这个方法
     */
    override fun createBindingParams() {}

    override fun onLazyInit() {}

    override fun initData() {}

    override  fun onPageReLoad() {}

    override fun isExitPage(): Boolean = false

    override fun showExitTips() {
        ToastUtils.show(R.string.core_exit_tips)
    }

    private var exitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isExitPage()) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                //弹出提示，可以有多种方式
                exitTime = System.currentTimeMillis()
                showExitTips()
            } else {
                requireActivity().finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}