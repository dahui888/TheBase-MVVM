package com.theone.demo.ui.fragment.integral

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.R
import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.demo.ui.adapter.IntegralRankAdapter
import com.theone.demo.ui.fragment.BasePagerListFragment
import com.theone.demo.ui.fragment.WebExplorerFragment
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
 * @describe 积分排行
 * @email 625805189@qq.com
 * @remark
 */
class IntegralRankFragment : BasePagerListFragment<IntegralResponse, IntegralRankViewModel>() {

    override fun createAdapter(): BaseQuickAdapter<IntegralResponse, *> = IntegralRankAdapter()

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            setTitleWithBackBtn("积分排行", this@IntegralRankFragment)
            addRightImageButton(
                R.drawable.svg_rank_rules,
                R.id.app_integral_rules
            ).setOnClickListener{
                startFragment(
                    WebExplorerFragment.newInstance(
                        BannerResponse(
                            title = "积分规则",
                            url = Url.INTEGRAL_RULES
                        )
                    )
                )
            }
        }
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}