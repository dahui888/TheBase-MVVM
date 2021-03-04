package com.theone.demo.data.bindadapter

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.layout.QMUIFrameLayout
import com.qmuiteam.qmui.widget.QMUIFloatLayout
import com.theone.demo.R
import com.theone.demo.app.util.ColorUtil
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.ui.fragment.SystemArticleFragment
import com.theone.mvvm.base.fragment.BaseFragment
import com.theone.mvvm.base.ext.util.dp2px
import com.theone.mvvm.base.ext.util.getDrawable

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 自定义 BindingAdapter
 */
object CustomBindAdapter {

    @BindingAdapter(value = ["treeChildData", "fragment"], requireAll = false)
    @JvmStatic
    fun loadTreeData(
        floatLayout: QMUIFloatLayout,
        classifyResponses: List<ClassifyResponse>,
        fragment: BaseFragment?
    ) {
        floatLayout.removeAllViews()
        val context = floatLayout.context
        for (c in classifyResponses) {
            val layoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
            val container = QMUIFrameLayout(context)
            val space = dp2px(context, 10)
            container.setPadding(0,0,space,space)
            val tag = TextView(context)
            val padding = dp2px(context, 4)
            val padding2 = padding * 2
            tag.run {
                setPadding(padding2, padding, padding2, padding)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                maxLines = 1
                setTextColor(ColorUtil.randomColor())
                text = c.name
                background = getDrawable(context, R.drawable.tree_tag_bg)
            }
            container.setOnClickListener{
                fragment?.startFragment(SystemArticleFragment.newInstance(c))
            }
            container.addView(tag,layoutParams)
            floatLayout.addView(container, layoutParams)
        }
    }

    @BindingAdapter(value = ["treeChildData", "fragment"], requireAll = false)
    @JvmStatic
    fun loadArticleTreeData(
        floatLayout: QMUIFloatLayout,
        articles: List<ArticleResponse>,
        fragment: BaseFragment?
    ) {
        floatLayout.removeAllViews()
        val context = floatLayout.context
        for (article in articles) {
            val layoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
            val container = QMUIFrameLayout(context)
            val space = dp2px(context, 10)
            container.setPadding(0,0,space,space)
            val tag = TextView(context)
            val padding = dp2px(context, 4)
            val padding2 = padding * 2
            tag.run {
                setPadding(padding2, padding, padding2, padding)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                maxLines = 1
                setTextColor(ColorUtil.randomColor())
                text = article.title
                background = getDrawable(context, R.drawable.tree_tag_bg)
            }
            container.addView(tag,layoutParams)
            container.setOnClickListener{

            }
            floatLayout.addView(container, layoutParams)
        }
    }

    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener) {
        checkbox.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(view: EditText, boolean: Boolean) {
        if (boolean) {
            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        view.setSelection(view.text.length)
    }

    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    @JvmStatic
    fun imageUrl(view: ImageView, url: String, placeholder: Drawable) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .placeholder(placeholder)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["visible"], requireAll = false)
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["circleImageUrl"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                action(s.toString())
            }
        })
    }

    @BindingAdapter("noRepeatClick")
    @JvmStatic
    fun setOnClick(view: View, clickListener: () -> Unit) {
        val mHits = LongArray(2)
        view.setOnClickListener {
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] < SystemClock.uptimeMillis() - 500) {
                clickListener.invoke()
            }
        }
    }


}