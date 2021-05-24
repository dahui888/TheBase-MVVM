package com.theone.mvvm.core.base.activity

import android.view.KeyEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import com.hjq.toast.ToastUtils
import com.theone.common.ext.logI
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.R
import com.theone.mvvm.core.callback.ICore
import com.theone.mvvm.core.ext.hideProgressDialog
import com.theone.mvvm.core.ext.registerLoadSir
import com.theone.mvvm.core.ext.showProgressDialog
import com.theone.mvvm.core.widge.loadsir.core.LoadService
import com.theone.mvvm.entity.ProgressBean
import com.theone.mvvm.ext.qmui.showMsgTipsDialog

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
 * @date 2021-04-16 15:19
 * @describe BaseCoreActivity
 * @email 625805189@qq.com
 * @remark 添加界面状态管理
 */
abstract class BaseCoreActivity<VM : BaseViewModel, DB : ViewDataBinding>:BaseVmDbActivity<VM,DB>(),
    ICore {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    override fun getLoadSir(): LoadService<Any>? = mLoadSir

    override fun setContentView(view: View?) {
        super.setContentView(view)
        mLoadSir = registerLoadSir()
    }

    override fun loadSirRegisterView(): View = getContentView()

    override fun showProgress(bean: ProgressBean) {
       showProgressDialog(bean)
    }

    override fun hideProgress() {
       hideProgressDialog()
    }

    /**
     * 这里把这些方法实现了，子类需要的时候重写，免的每次都要去实现这个方法
     */
    override fun createBindingParams() {}

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
                exitTime = System.currentTimeMillis()
                showExitTips()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}