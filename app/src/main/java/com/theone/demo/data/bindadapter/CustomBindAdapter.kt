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
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.layout.QMUIFrameLayout
import com.qmuiteam.qmui.widget.QMUIFloatLayout
import com.theone.common.ext.dp2px
import com.theone.common.ext.getDrawable
import com.theone.demo.R
import com.theone.demo.app.util.ColorUtil
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.ClassifyResponse
import com.theone.demo.data.model.bean.SearchResponse
import com.theone.demo.ui.fragment.category.SystemArticleFragment
import com.theone.demo.ui.fragment.web.WebExplorerFragment
import com.theone.common.util.DateFormatUtils


object CustomBindAdapter {

    @BindingAdapter(value = ["longDate","type"])
    @JvmStatic
    fun formatDate(
        textView: TextView,
        longDate: Long,
        type: Int
    ) {
       textView.text = DateFormatUtils.formatTimeStampString(textView.context,longDate,type)
    }

    @BindingAdapter(value = ["treeChildData", "fragment"], requireAll = false)
    @JvmStatic
    fun loadTreeData(
        floatLayout: QMUIFloatLayout,
        classifyResponses: List<ClassifyResponse>,
        fragment: QMUIFragment?
    ) {
        floatLayout.removeAllViews()
        for (c in classifyResponses) {
            createFloatLayoutItem(floatLayout,c.name){
                fragment?.startFragment(SystemArticleFragment.newInstance(c))
            }
        }
    }

    @BindingAdapter(value = ["treeChildData", "fragment"], requireAll = false)
    @JvmStatic
    fun loadArticleTreeData(
        floatLayout: QMUIFloatLayout,
        articles: List<ArticleResponse>,
        fragment: QMUIFragment?
    ) {
        floatLayout.removeAllViews()
        for (article in articles) {
            createFloatLayoutItem(floatLayout,article.title){
                fragment?.startFragment(WebExplorerFragment.newInstance(article))
            }
        }
    }

    fun loadHotSearchData(
        floatLayout: QMUIFloatLayout,
        articles: List<SearchResponse>,
        callback: ((String) -> Unit?)? = null
    ) {
        floatLayout.removeAllViews()
        for (article in articles) {
            createFloatLayoutItem(floatLayout,article.name,callback)
        }
    }

    private fun createFloatLayoutItem(
        floatLayout: QMUIFloatLayout,title:String,callback: ((String) -> Any?)? = null){
        val context = floatLayout.context
        val layoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
        val container = QMUIFrameLayout(context)
        val space = context.dp2px(10)
        container.setPadding(0,0,space,space)
        val tag = TextView(context)
        val padding = context.dp2px( 4)
        val padding2 = padding * 2
        tag.run {
            setPadding(padding2, padding, padding2, padding)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            maxLines = 1
            setTextColor(ColorUtil.randomColor())
            text = title
            background = getDrawable(
                context,
                R.drawable.tree_tag_bg
            )
        }
        container.addView(tag,layoutParams)
        callback?.run {
            container.setOnClickListener{
                invoke(title)
            }
        }
        floatLayout.addView(container, layoutParams)
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
    fun imageUrl(view: ImageView, url: String,  placeholder: Drawable?) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .placeholder(placeholder ?: getDrawable(view.context,R.drawable.image_place_holder))
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