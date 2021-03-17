package com.theone.demo.ui.fragment.mine

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.demo.ui.adapter.IntegralRankAdapter
import com.theone.demo.ui.fragment.BasePagerListFragment
import com.theone.demo.viewmodel.RankViewModel
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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class RankFragment:BasePagerListFragment<IntegralResponse,RankViewModel>() {

    override fun createAdapter(): BaseQuickAdapter<IntegralResponse, *> = IntegralRankAdapter()

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            setTitleWithBackBtn("积分排行",this@RankFragment)
        }
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}