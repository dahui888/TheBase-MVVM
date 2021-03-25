package com.theone.demo.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ZoomButtonsController
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient
import com.theone.demo.R
import com.theone.demo.app.widge.QDWebView
import com.theone.demo.data.model.bean.IWeb
import com.theone.demo.databinding.FragmentWebExploererBinding
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn
import com.theone.mvvm.ext.showViews
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.fragment.BaseCoreFragment
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
        fun <T:IWeb>newInstance(data: T): WebExplorerFragment {
            val fragment = WebExplorerFragment()
            val bundle = Bundle()
            bundle.putParcelable("DATA", data)
            fragment.arguments = bundle
            return fragment
        }

        const val PROGRESS_PROCESS: Int = 0
        const val PROGRESS_GONE: Int = 1
    }

    private lateinit var mIWeb: IWeb
    private var mWebView: QDWebView? = null
    private val mProgressHandler: ProgressHandler by lazy { ProgressHandler() }

    private fun needDispatchSafeAreaInset(): Boolean = false

    override fun showTitleBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.fragment_web_exploerer

    override fun initView(rootView: View) {
        mIWeb = requireArguments().getParcelable("DATA")!!
        initTopBar()
    }

    private fun initTopBar() {
        mBinding.topbar.run {
            setTitleWithBackBtn(mIWeb.getWebTitle(),this@WebExplorerFragment)
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
            if(newProgress > fragment.mProgressHandler.mDstProgressIndex){
                fragment.sendProgressMessage(PROGRESS_PROCESS,newProgress,100)
            }
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)

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

    inner class ProgressHandler : Handler() {

         var mDstProgressIndex: Int = 0
         var mDuration: Int = 0
         var mAnimator: ObjectAnimator? = null

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                PROGRESS_PROCESS -> {
                    mDstProgressIndex = msg.arg1
                    mDuration = msg.arg2
                    showViews(mBinding.progressbar)
                    if (null != mAnimator && mAnimator?.isRunning!!) {
                        mAnimator?.cancel()
                    }
                    mAnimator = ObjectAnimator.ofInt(mBinding.progressbar, "progress", mDstProgressIndex)
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