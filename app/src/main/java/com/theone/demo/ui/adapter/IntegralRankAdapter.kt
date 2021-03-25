package com.theone.demo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.demo.databinding.ItemArticleBinding
import com.theone.demo.databinding.ItemIntegralRankBinding
import com.theone.demo.ui.fragment.ArticleFragment
import com.theone.demo.viewmodel.ArticleViewModel
import com.theone.mvvm.core.adapter.TheBaseQuickAdapter


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
 * @describe 积分排行
 * @email 625805189@qq.com
 * @remark
 */
class IntegralRankAdapter : TheBaseQuickAdapter<IntegralResponse, ItemIntegralRankBinding>(
    R.layout.item_integral_rank
) {

    override fun convert(holder: BaseDataBindingHolder<ItemIntegralRankBinding>, data: IntegralResponse) {
       holder.dataBinding?.run {
           item = data
       }
    }

}