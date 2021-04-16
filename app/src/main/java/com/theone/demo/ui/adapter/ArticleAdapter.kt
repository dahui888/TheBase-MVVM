package com.theone.demo.ui.adapter

import android.util.SparseArray
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.databinding.ItemArticleBinding
import com.theone.mvvm.core.base.adapter.TheBaseQuickAdapter


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
class ArticleAdapter : TheBaseQuickAdapter<ArticleResponse, ItemArticleBinding>(
    R.layout.item_article
)