package com.theone.demo.ui.fragment.share

import android.view.View
import android.widget.TextView
import com.qmuiteam.qmui.skin.QMUISkinHelper
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import com.qmuiteam.qmui.widget.popup.QMUIPopups
import com.theone.demo.R
import com.theone.demo.app.ext.isShareAutoPass
import com.theone.demo.databinding.FragmentArticleAddBinding
import com.theone.demo.viewmodel.AddShareArticleViewModel
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.IClick
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn
import com.theone.mvvm.ext.qmui.showFailTipsDialog
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import com.theone.mvvm.ext.qmui.showSuccessTipsExitDialog


// ┏┓　 ┏┓
//┏┛┻━━━┛┻┓
//┃　　　　 ┃
//┃　　━　  ┃
//┃ ┳┛　┗┳　┃
//┃　　　　 ┃
//┃　　┻　  ┃
//┃　　　　 ┃
//┗━┓　　┏━┛
//   ┃　  ┃                  神兽保佑
//   ┃　　┃                  永无BUG！
//   ┃　　┗━━━┓
//   ┃　　　　┣┓
//   ┃　　　　┏┛
//   ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021/3/18 0018
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class AddShareArticleFragment :
    BaseCoreFragment<AddShareArticleViewModel, FragmentArticleAddBinding>() {

    val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private val mRulesPopup: QMUIPopup by lazy {
        createRulesPopup()
    }

    override fun getLayoutId(): Int = R.layout.fragment_article_add

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setTitleWithBackBtn("添加分享", this@AddShareArticleFragment)
            addRightImageButton(
                R.drawable.svg_rank_rules,
                R.id.topbar_right_view
            ).setOnClickListener {
//                showRulesDialog()
                showRulesPopup(it)
            }
        }
        mAppVm.userInfo.value?.let {
            mViewModel.publisher.set(it.getUserName())
        }
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@AddShareArticleFragment) {
                // 不需要审核的官方链接才自动刷新
                if (isShareAutoPass(mViewModel.url.get()))
                    mAppVm.shareArticle.value = true
                showSuccessTipsExitDialog("添加成功")
            }
        }
    }

    private fun showRulesDialog() {
        QMUIDialog.MessageDialogBuilder(context)
            .setTitle("温馨提示")
            .setMessage(R.string.share_article_rules)
            .addAction(
                0,
                "确定", QMUIDialogAction.ACTION_PROP_POSITIVE
            ) { dialog, index ->
                dialog.dismiss()
            }
            .show()
            .setCanceledOnTouchOutside(false)

//        context?.showMsgDialog("温馨提示", getString(R.string.share_article_rules), leftAction = null,
//            listener = QMUIDialogAction.ActionListener { dialog, index ->
//                dialog.dismiss()
//            })?.setCanceledOnTouchOutside(false)
    }

    private fun showRulesPopup(view: View) {
        mRulesPopup.show(view)
    }

    private fun createRulesPopup(): QMUIPopup {
        val textView = TextView(context)
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(context, 4).toFloat(), 1.0f)
        val padding = QMUIDisplayHelper.dp2px(context, 20)
        textView.setPadding(padding, padding, padding, padding)
        textView.text = getString(R.string.share_article_rules)
        val builder = QMUISkinValueBuilder.acquire()
        QMUISkinHelper.setSkinValue(textView, builder)
        builder.release()
        return QMUIPopups.popup(context, QMUIDisplayHelper.dp2px(context, 250))
            .preferredDirection(QMUIPopup.DIRECTION_TOP)
            .view(textView)
            .shadowElevation(QMUIDisplayHelper.dp2px(context, 5), 0.55f)
            .edgeProtection(QMUIDisplayHelper.dp2px(context, 10))
            .offsetX(QMUIDisplayHelper.dp2px(context, 20))
            .offsetYIfBottom(QMUIDisplayHelper.dp2px(context, 5))
            .shadow(true)
            .arrow(true)
            .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER).dimAmount(0.5f)
    }

    override fun getBindingClick(): IClick? = ProxyClick()

    inner class ProxyClick:IClick {

        fun add() {
            when {
                mViewModel.title.get().isEmpty() -> showFailTipsDialog("标题不能为空")
                mViewModel.url.get().isEmpty() -> showFailTipsDialog("链接不能为空")
                else -> {
                    mViewModel.requestServer()
                }
            }
        }
    }
}