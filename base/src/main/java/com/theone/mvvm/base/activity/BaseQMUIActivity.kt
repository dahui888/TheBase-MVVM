package com.theone.mvvm.base.activity

import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.mvvm.R
import com.theone.mvvm.base.IQMUIBase
import com.theone.mvvm.ext.createTopBar
import com.theone.mvvm.ext.createView
import com.theone.mvvm.ext.qmui.updateStatusBarMode

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
 * @date 2021-03-31 14:26
 * @describe 对 QMUIActivity
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseQMUIActivity : QMUIActivity(), IQMUIBase {

    protected val TAG: String = this.javaClass.simpleName

    /**
     * 内容层
     */
    private val mContent: View by lazy {
        createContentView()
    }

    /**
     * 内容层
     */
    private val mTopBar: QMUITopBarLayout? by lazy {
        createTopBar(this)
    }

    override fun getTopBar(): QMUITopBarLayout? = mTopBar

    override fun showTopBar(): Boolean = true

    /**
     * 这个方法是为了给BaseVmDbActivity重写绑定视图的
     * 所以仅供此包类使用
     */
    internal open fun createContentView(): View = layoutInflater.inflate(getLayoutId(), null)

    override fun getContentView(): View = mContent

    override fun isStatusBarLightMode(): Boolean = true

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.scale_enter, R.anim.slide_still)
        updateStatusBarMode(isStatusBarLightMode())
        createView(this, translucentFull()).let {
            setContentView(it)
            init(it)
        }
    }

    internal open fun init(root: View){
        initView(root)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_still, R.anim.scale_exit)
    }

}