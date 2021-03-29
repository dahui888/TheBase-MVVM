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
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.theone.mvvm.R
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
 * @date 2021/2/22 0022
 * @describe BaseFragment
 * @email 625805189@qq.com
 * @remark 懒加载+TopBar+界面状态管理+状态栏等的封装
 */

val match_wrap : ViewGroup.LayoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)

abstract class BaseQMUIFragment : QMUIFragment(), LifecycleObserver {

    val TAG: String = this.javaClass.simpleName

    lateinit var mActivity: AppCompatActivity

    private var mTopBar: QMUITopBarLayout? = null
    protected val mBody: View by lazy {
        createContentView()
    }

    /**
     * 是否为根Fragment： getParentFragment() 为空
     */
    private var isIndexFragment = false
    private var mIsFirstLayInit = true

    abstract fun getLayoutId(): Int

    abstract fun initView(rootView: View)

    internal open fun createContentView(): View = layoutInflater.inflate(getLayoutId(), null)
    open fun showTitleBar(): Boolean = isIndexFragment


    override fun onCreateView(): View {
        if (showTitleBar()) {
            val root = QMUIWindowInsetLayout(mActivity)
            root.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            mBody.fitsSystemWindows = !translucentFull()
            root.addView(mBody)
            // 这个一定要放在addView后面
            if (!translucentFull()) {
                val margin = QMUIResHelper.getAttrDimen(
                    mActivity,
                    R.attr.qmui_topbar_height
                )
                mBody.layoutParams.let {
                    if (it is ViewGroup.MarginLayoutParams) {
                        it.setMargins(0, margin, 0, 0)
                        mBody.requestLayout()
                    }
                }
            }
            mTopBar = createQMUITopBarLayout()
            root.addView(mTopBar)
            return root
        }
        return mBody
    }

    override fun onViewCreated(rootView: View) {
        mIsFirstLayInit = true
        initView(rootView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyViewLifecycleOwner.lifecycle.addObserver(this)
    }

    private fun createQMUITopBarLayout(): QMUITopBarLayout? {
        return QMUITopBarLayout(mActivity).apply {
            layoutParams = match_wrap
            fitsSystemWindows = true
        }
    }

    protected open fun getTopBar(): QMUITopBarLayout? = mTopBar

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


    /**
     * 懒加载分两种情况
     * 1.在动画结束后开始进行加载
     * 2.当前Fragment为子Fragment时，比如ViewPager的ItemFragment,或者点击切换的，这种情况下当界面可见时才进行加载
     *
     * 这里自动根据 [isIndexFragment] 判断是以哪种情况进行懒加载
     */
    abstract fun onLazyInit()

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

    private fun checkLazyInit() {
        if (mIsFirstLayInit) {
            view?.post {
                mIsFirstLayInit = false
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
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     * @remark 根据自己APP的配色，给定一个全局的默认模式。个人建议用TopBar的背景颜色做判断。
     */
    protected open fun isStatusBarLightMode(): Boolean {
        return true
    }

    /**
     * 向外提供的关闭方法
     */
    open fun finish() {
        // 这里一定要用这个方法
        popBackStackAfterResume()
        // 不能用这个
        // popBackStack()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onLazyPause() {
        QMUIKeyboardHelper.hideKeyboard(view)
    }

}