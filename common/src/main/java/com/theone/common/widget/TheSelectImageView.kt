package com.theone.common.widget

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import com.theone.common.R

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
 * @date 2021-04-14 09:23
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TheSelectImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyle) {

    /**
     * 普通状态时的图片
     */
    var img_normal: Drawable? = null
        set(value) {
            field = value
            setDefaultImage()
        }

    /**
     * 选中状态时的图片
     */
    var img_selected: Drawable? = null

    /**
     * 当前选中状态
     */
    var mIsSelected = false

    /**
     * 标记 - 是否按下了
     */
    var down = false

    /**
     * 动画时间
     */
    var mAnimationDuration: Float = 0f

    /**
     * 状态监听
     */
    var listener: OnSelectChangedListener? = null

    /**
     * 长按是否视为点击也需要更换
     */
    var mLongClickEnable = false

    /**
     * 最低按压时间视为长按？
     */
    var mPressMinTime = 0f

    /**
     * 按下时的时间 - 用于计算是否视为长按
     */
    var downMillions = 0L

    init {
        context.obtainStyledAttributes(attrs, R.styleable.TheSelectImageView).run {
            img_normal = getDrawable(R.styleable.TheSelectImageView_img_normal)
            img_selected = getDrawable(R.styleable.TheSelectImageView_img_selected)
            mAnimationDuration = getFloat(R.styleable.TheSelectImageView_animal_duration, 150f)
            mPressMinTime = getFloat(R.styleable.TheSelectImageView_press_min_time, 1000f)
            mLongClickEnable = getBoolean(R.styleable.TheSelectImageView_long_click_enable,false)
            recycle()
        }
        setDefaultImage()
    }

    private fun setDefaultImage() {
        img_normal?.let {
            background = img_normal
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.action.let {
            if (it == MotionEvent.ACTION_DOWN) {
                down = true
                downMillions = System.currentTimeMillis()
                return true
            }
            if (it == MotionEvent.ACTION_UP && down) {
                down = false
                // 拒绝长按？如果是长按，不视为点击，不做切换处理
                if (mLongClickEnable && System.currentTimeMillis() - downMillions < mPressMinTime) {
                    updateSelect()
                    startAnimation()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun startAnimation() {
        createViewPropertyAnimation(0f, object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                createViewPropertyAnimation(1.0f).start()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        }).start()
    }

    //***   同样的动画，两种不同的写法 ****/////
    private fun createViewPropertyAnimation(
        scale: Float,
        listener: AnimatorListener? = null
    ): ViewPropertyAnimator {
        return animate().scaleX(scale)
            .scaleY(scale)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(mAnimationDuration.toLong())
            .setListener(listener)
    }

    private fun createObjectAnimation(
        scale: Float,
        listener: AnimatorListener? = null
    ): ObjectAnimator {
        val holder1 = PropertyValuesHolder.ofFloat("scaleX", scale)
        val holder2 = PropertyValuesHolder.ofFloat("scaleY", scale)
        return ObjectAnimator.ofPropertyValuesHolder(this, holder1, holder2).apply {
            addListener(listener)
        }
    }

    /**
     * 更新选中状态
     */
    private fun updateSelect() {
        // 更改选中标记状态
        mIsSelected = !mIsSelected
        // 根据状态切换不同的背景
        this.background = if (mIsSelected) img_selected else img_normal
        // 状态监听
        listener?.onSelectChanged(mIsSelected)
    }

    interface OnSelectChangedListener {

        fun onSelectChanged(isSelected: Boolean)

    }


}