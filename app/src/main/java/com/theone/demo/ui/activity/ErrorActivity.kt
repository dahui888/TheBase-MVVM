package com.theone.demo.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import androidx.core.content.getSystemService
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.theone.demo.R
import com.theone.demo.app.ext.joinQQGroup
import com.theone.demo.databinding.ActivityErrorBinding
import com.theone.demo.databinding.FragmentTestBinding
import com.theone.mvvm.base.activity.BaseQMUIActivity
import com.theone.mvvm.base.activity.BaseVmDbActivity
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.qmui.showMsgDialog
import com.theone.mvvm.ext.qmui.showMsgTipsDialog
import kotlinx.android.synthetic.main.activity_error.*

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
 * @date 2021-03-31 15:21
 * @describe 崩溃后错误显示界面
 * @email 625805189@qq.com
 * @remark
 */
class ErrorActivity : BaseVmDbActivity<BaseViewModel,ActivityErrorBinding>() {

    private val config: CaocConfig? by lazy {
        CustomActivityOnCrash.getConfigFromIntent(intent)
    }

    private val errorMsg:String by lazy {
        CustomActivityOnCrash.getAllErrorDetailsFromIntent(this,intent)
    }

    override fun translucentFull(): Boolean =  true

    override fun getLayoutId(): Int = R.layout.activity_error

    override fun initView(root:View) {
        getTopBar()?.run {
            setTitle(R.string.crash_title)
            updateBottomDivider(0,0,0,0)
        }
    }

    override fun initData() {
        mBinding.click = Proxy()
    }

    override fun createObserver() {}

    inner class Proxy{

        fun showErrorMsgDialog(){
            showMsgDialog("错误信息",errorMsg,leftAction = null,rightAction = "关闭",listener = QMUIDialogAction.ActionListener { dialog, index ->
                dialog.dismiss()
            },prop = QMUIDialogAction.ACTION_PROP_NEGATIVE)
        }

        fun restart(){
            config?.let {
                CustomActivityOnCrash.restartApplication(this@ErrorActivity,it)
            }
        }

        fun sendError(){
            val mClipData = ClipData.newPlainText("errorLog",errorMsg)
            // 将ClipData内容放到系统剪贴板里。
            getSystemService<ClipboardManager>()?.setPrimaryClip(mClipData)
            showMsgTipsDialog("已复制错误日志,请粘贴发送至QQ群",delay = 2000){
                joinQQGroup("26hK_GKmpQJbBHpfPIMlJztVmzTRyzZp")
            }
        }

    }


}