package com.theone.mvvm.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.theone.mvvm.widge.loadsir.core.LoadService
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.theone.mvvm.R
import com.theone.mvvm.base.ext.loadSirInit
import com.theone.mvvm.base.ext.qmui.showLoadingDialog
import com.theone.mvvm.base.ext.setMargin
import com.theone.mvvm.base.ext.util.logE


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
abstract class BaseFragment : QMUIFragment(), LifecycleObserver {

    lateinit var mActivity: AppCompatActivity

    lateinit var mBody: View

    //界面状态管理者
    lateinit var mLoadSir: LoadService<Any>
    private var mTopBar: QMUITopBarLayout? = null

    /**
     * 是否为根Fragment： getParentFragment() 为空
     */
    private var isIndexFragment = false
    private var mIsFirstLayInit = true

    abstract fun getLayoutId(): Int

    abstract fun initView(rootView: View)

    internal open fun createContentView(): View = layoutInflater.inflate(getLayoutId(), null)
    open fun showTitleBar(): Boolean = isIndexFragment

    protected open fun onReLoad() {}

    override fun onCreateView(): View {
        mBody = createContentView()
        if (showTitleBar()) {
            val root = QMUIWindowInsetLayout(mActivity)
            root.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            mBody.fitsSystemWindows = !translucentFull()
            root.addView(mBody)
            // 这个一定要放在addView后面
            if(!translucentFull()){
                mBody.setMargin(
                    0,
                    QMUIResHelper.getAttrDimen(mActivity, R.attr.qmui_topbar_height),
                    0,
                    0
                )
            }
            root.addView(createQMUITopBarLayout())
            return root
        }
        return mBody
    }

    override fun onViewCreated(rootView: View) {
        initView(rootView)
        mLoadSir = loadSirInit(mBody) {
            onReLoad()
        }
    }

    private fun createQMUITopBarLayout(): QMUITopBarLayout {
        mTopBar = QMUITopBarLayout(mActivity)
        mTopBar!!.layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
        mTopBar!!.fitsSystemWindows = true
        return mTopBar!!
    }

    protected open fun getTopBar():QMUITopBarLayout? = mTopBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyViewLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isIndexFragment = null == parentFragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onLazyResume() {
        if (isNeedChangeStatusBarMode()) {
            updateStatusBarMode(isStatusBarLightMode())
        }
        if (!isIndexFragment) {
            checkLazyInit()
        }
    }

    override fun onEnterAnimationEnd(animation: Animation?) {
        super.onEnterAnimationEnd(animation)
        if (isIndexFragment) {
            checkLazyInit()
        }
    }

    /**
     * 懒加载分两种情况
     * 1.在动画结束后开始进行加载
     * 2.当前Fragment为子Fragment时，比如ViewPager的ItemFragment,或者点击切换的，这种情况下当界面可见时才进行加载
     *
     * 这里自动根据 [.isIndexFragment] 判断是以哪种情况进行懒加载
     */
    abstract fun onLazyInit()

    private fun checkLazyInit() {
        if (mIsFirstLayInit) {
            mIsFirstLayInit = false
            view?.post {
                onLazyInit()
            }
        }
    }

    /**
     * @return 是否要进行对状态栏的处理
     * @remark 默认当为根fragment时才进行处理
     */
    protected open fun isNeedChangeStatusBarMode(): Boolean {
        return isIndexFragment
    }

    /**
     * @return 是否设置状态栏LightMode 深色图标 白色背景
     * @remark 默认根据当前TopBarLayout的背景颜色是否为白色或是否存在渐变色背景进行判断
     */
    protected open fun isStatusBarLightMode(): Boolean {
        return true
    }

    /**
     * 更新状态栏模式
     *
     * @param isLight true 设置状态栏黑色字体图标，
     *
     * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
     */
    protected open fun updateStatusBarMode(isLight: Boolean) {
        if (isLight) {
            QMUIStatusBarHelper.setStatusBarLightMode(mActivity)
        } else {
            QMUIStatusBarHelper.setStatusBarDarkMode(mActivity)
        }
    }

    fun dp2px(dp: Int): Int = QMUIDisplayHelper.dp2px(mActivity, dp)
    fun sp2px(dp: Int): Int = QMUIDisplayHelper.sp2px(mActivity, dp)

    /**
     * 向外提供的关闭方法
     */
    open fun finish() {
        popBackStackAfterResume()
    }
    override fun onPause() {
        super.onPause()
        QMUIKeyboardHelper.hideKeyboard(view)
    }

}