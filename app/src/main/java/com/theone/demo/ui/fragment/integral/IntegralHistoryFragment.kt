package com.theone.demo.ui.fragment.integral

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.R
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.data.model.bean.IntegralHistoryResponse
import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.demo.ui.adapter.IntegralHistoryAdapter
import com.theone.demo.ui.adapter.IntegralRankAdapter
import com.theone.demo.ui.fragment.BasePagerListFragment
import com.theone.demo.ui.fragment.WebExplorerFragment
import com.theone.demo.viewmodel.IntegralHistoryViewModel
import com.theone.demo.viewmodel.IntegralRankViewModel
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
 * @date 2021/3/17 0017
 * @describe 积分记录
 * @email 625805189@qq.com
 * @remark
 */
class IntegralHistoryFragment:BasePagerListFragment<IntegralHistoryResponse,IntegralHistoryViewModel>() {

    override fun createAdapter(): BaseQuickAdapter<IntegralHistoryResponse, *> = IntegralHistoryAdapter()

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            setTitleWithBackBtn("积分记录",this@IntegralHistoryFragment)
        }
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}