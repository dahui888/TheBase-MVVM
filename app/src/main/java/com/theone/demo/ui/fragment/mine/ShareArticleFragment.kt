package com.theone.demo.ui.fragment.mine

import android.view.View
import com.theone.demo.ui.fragment.ArticleFragment
import com.theone.demo.viewmodel.ShareArticleViewModel
import com.theone.mvvm.base.ext.qmui.setTitleWithBackBtn


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
 * @date 2021/3/5 0005
 * @describe 我分享的文章
 * @email 625805189@qq.com
 * @remark
 */
class ShareArticleFragment:
    ArticleFragment<ShareArticleViewModel>() {

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.setTitleWithBackBtn("分享的文章",this)
    }

}