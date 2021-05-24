package com.theone.demo.ui.fragment.web

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.ZoomButtonsController
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient
import com.theone.demo.R
import com.theone.demo.app.widget.QDWebView
import com.theone.demo.data.model.bean.IWeb
import com.theone.demo.databinding.FragmentWebExploererBinding
import com.theone.common.ext.showViews
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.common.constant.BundleConstant
import com.theone.common.ext.dp2px
import com.theone.common.ext.getValueNonNull
import com.theone.common.ext.toHtml
import com.theone.common.widget.TheMarqueeTextView
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import java.lang.reflect.Field


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
 * @date 2021/3/8 0008
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class WebExplorerFragment : BaseCoreFragment<BaseViewModel, FragmentWebExploererBinding>() {

    companion object {
        fun <T : IWeb> newInstance(data: T): WebExplorerFragment {
            return WebExplorerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BundleConstant.DATA, data)
                }
            }
        }

        const val PROGRESS_PROCESS: Int = 0
        const val PROGRESS_GONE: Int = 1
    }

    private val mIWeb: IWeb by getValueNonNull(BundleConstant.DATA)
    private var mWebView: QDWebView?=null
    private val mProgressHandler: ProgressHandler by lazy { ProgressHandler() }
    private lateinit var mTitleView: TheMarqueeTextView

    private fun needDispatchSafeAreaInset(): Boolean = false

    override fun showTopBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.fragment_web_exploerer

    override fun initView(rootView: View) {
        initTopBar()
    }

    private fun initTopBar() {
        mBinding.topbar.run {
            addLeftBackImageButton().setOnClickListener {
                finish()
            }
            // QMUI的Title用的是QMUIQQFaceView，无法使用跑马灯效果，这里重新设置一个
            // setTitle(mIWeb.getWebTitle().toHtml().toString())
            mTitleView = TheMarqueeTextView(mActivity).apply {
                layoutParams = RelativeLayout.LayoutParams(matchParent, wrapContent).apply {
                    addRule(RelativeLayout.RIGHT_OF, R.id.qmui_topbar_item_left_back)
                    gravity = Gravity.CENTER
                    marginEnd = dp2px(20)
                }
                marqueeRepeatLimit = Int.MAX_VALUE
                isFocusable = true
                textSize = 17f
                ellipsize = TextUtils.TruncateAt.MARQUEE
                isSingleLine = true
                setHorizontallyScrolling(true)
                isFocusableInTouchMode = true
                text = mIWeb.getWebTitle().toHtml()
            }
            setCenterView(mTitleView)
        }
    }

    override fun onLazyInit() {
        initWebView()
    }

    private fun initWebView() {
        val needDispatchSafeAreaInset = needDispatchSafeAreaInset()
        mWebView = QDWebView(context)
        mBinding.mWebContainer.run {
            addWebView(mWebView!!, needDispatchSafeAreaInset)
            val params = layoutParams as FrameLayout.LayoutParams
            fitsSystemWindows = !needDispatchSafeAreaInset
            params.topMargin = if (needDispatchSafeAreaInset) 0 else QMUIResHelper.getAttrDimen(
                context,
                R.attr.qmui_topbar_height
            )
            layoutParams = params
        }

        mWebView!!.run {
            webChromeClient = ExplorerWebViewChromeClient(this@WebExplorerFragment)
            webViewClient = ExplorerWebViewClient(needDispatchSafeAreaInset)
            setZoomControlGone(this)
            loadUrl(mIWeb.getWebUrl())
        }
    }

    private fun sendProgressMessage(
        progressType: Int,
        newProgress: Int,
        duration: Int
    ) {
        val msg = Message()
        msg.what = progressType
        msg.arg1 = newProgress
        msg.arg2 = duration
        mProgressHandler.sendMessage(msg)
    }

    private fun setZoomControlGone(webView: WebView) {
        webView.settings.displayZoomControls = false
        val classType: Class<*>
        val field: Field
        try {
            classType = WebView::class.java
            field = classType.getDeclaredField("mZoomButtonsController")
            field.isAccessible = true
            val zoomButtonsController = ZoomButtonsController(
                webView
            )
            zoomButtonsController.zoomControls.visibility = View.GONE
            try {
                field[webView] = zoomButtonsController
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    inner class ExplorerWebViewChromeClient(val fragment: WebExplorerFragment) : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress > fragment.mProgressHandler.mDstProgressIndex) {
                fragment.sendProgressMessage(PROGRESS_PROCESS, newProgress, 100)
            }
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
//            mTitleView.setText(title)
        }

        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
            callback?.onCustomViewHidden()
        }

    }

    inner class ExplorerWebViewClient(needDispatchSafeAreaInset: Boolean) :
        QMUIWebViewClient(needDispatchSafeAreaInset, true) {
        override fun onPageStarted(
            view: WebView,
            url: String,
            favicon: Bitmap?
        ) {
            super.onPageStarted(view, url, favicon)
            if (mProgressHandler.mDstProgressIndex == 0) {
                sendProgressMessage(
                    PROGRESS_PROCESS,
                    30,
                    500
                )
            }
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let {
                if (it.startsWith("http:") || it.startsWith("https:")) {
                    view?.loadUrl(it)
                    return false
                }
            }
            return true
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            handler?.proceed()
        }

        override fun onPageFinished(
            view: WebView,
            url: String
        ) {
            super.onPageFinished(view, url)
            sendProgressMessage(
                PROGRESS_GONE,
                100,
                0
            )
        }
    }

    @SuppressLint("HandlerLeak")
    inner class ProgressHandler : Handler() {

        var mDstProgressIndex: Int = 0
        var mDuration: Int = 0
        var mAnimator: ObjectAnimator?=null

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                PROGRESS_PROCESS -> {
                    mDstProgressIndex = msg.arg1
                    mDuration = msg.arg2
                    showViews(mBinding.progressbar)
                    if (null != mAnimator && mAnimator?.isRunning!!) {
                        mAnimator?.cancel()
                    }
                    mAnimator =
                        ObjectAnimator.ofInt(mBinding.progressbar, "progress", mDstProgressIndex)
                    mAnimator?.run {
                        duration = mDuration.toLong()
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                if (mBinding.progressbar?.progress == 100) {
                                    sendEmptyMessageDelayed(
                                        PROGRESS_GONE,
                                        500
                                    )
                                }
                            }
                        })
                        start()
                    }
                }
                PROGRESS_GONE -> {
                    mDstProgressIndex = 0
                    mDuration = 0
                    mBinding.progressbar.progress = 0
                    mBinding.progressbar.visibility = View.GONE
                    if (mAnimator != null && mAnimator!!.isRunning) {
                        mAnimator!!.cancel()
                    }
                    mAnimator = ObjectAnimator.ofInt(mBinding.progressbar, "progress", 0)
                    mAnimator?.run {
                        duration = 0
                        removeAllListeners()
                    }
                }
            }
        }

    }

    override fun initData() {
    }

    override fun createObserver() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.mWebContainer.destroy()
        mWebView = null
    }

}