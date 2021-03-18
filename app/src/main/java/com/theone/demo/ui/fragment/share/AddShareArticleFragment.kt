package com.theone.demo.ui.fragment.share

import android.view.View
import com.theone.demo.R
import com.theone.demo.databinding.FragmentArticleAddBinding
import com.theone.demo.viewmodel.AddShareArticleViewModel
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.setTitleWithBackBtn
import com.theone.mvvm.base.ext.qmui.showFailDialog
import com.theone.mvvm.base.ext.qmui.showSuccessDialog
import com.theone.mvvm.base.ext.qmui.showSuccessExitDialog
import com.theone.mvvm.base.fragment.BaseVmDbFragment


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
 * @date 2021/3/18 0018
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class AddShareArticleFragment:BaseVmDbFragment<AddShareArticleViewModel,FragmentArticleAddBinding>() {


    val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    override fun getLayoutId(): Int = R.layout.fragment_article_add

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setTitleWithBackBtn("添加分享",this@AddShareArticleFragment)
        }
        mAppVm.userInfo.value?.let {
            mViewModel.publisher.set(it.getUserName())
        }
    }

    override fun onLazyInit() {

    }

    override fun initData() {
        mBinding.run {
            vm = mViewModel
            click = ProxyClick()
        }
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@AddShareArticleFragment){
                mAppVm.shareArticle.value = true
                showSuccessExitDialog("添加成功")
            }
        }
    }

    inner class ProxyClick {

        fun add() {
            when {
                mViewModel.title.get().isEmpty() -> showFailDialog("标题不能为空")
                mViewModel.url.get().isEmpty() -> showFailDialog("链接不能为空")
                else -> {
                    mViewModel.requestServer()
                }
            }
        }
    }
}