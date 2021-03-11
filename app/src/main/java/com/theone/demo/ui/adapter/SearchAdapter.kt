package com.theone.demo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.databinding.ItemArticleBinding
import com.theone.demo.databinding.ItemSearchBinding
import com.theone.demo.ui.fragment.ArticleFragment
import com.theone.demo.viewmodel.ArticleViewModel


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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SearchAdapter : BaseQuickAdapter<String,BaseDataBindingHolder<ItemSearchBinding>>(
    R.layout.item_search
),LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemSearchBinding>, item: String) {
       holder.dataBinding?.run {
           data = item
       }
    }

}