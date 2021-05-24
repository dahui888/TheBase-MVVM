package com.theone.mvvm.core.ext

import android.app.Activity
import android.content.Intent
import com.theone.common.callback.IImageUrl
import com.theone.common.constant.BundleConstant
import com.theone.common.ext.logI
import com.theone.mvvm.core.base.activity.ImagePreviewActivity
import com.theone.mvvm.core.data.entity.ImagePreviewBean
import com.theone.mvvm.core.data.entity.ImagePreviewEvent

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
 * @date 2021-04-25 10:30
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun <T : IImageUrl> startImagePreview(
    activity: Activity,
    target: Class<*> = ImagePreviewActivity::class.java,
    images: List<T>,
    position: Int = 0,
    needDown: Boolean = true,
    isWhiteTheme: Boolean = false
) {
    if (activity.isDestroyed) return
    val previewBeans = mutableListOf<ImagePreviewBean>()
    for (image in images) {
        val bean = ImagePreviewBean().apply {
            with(image) {
                url = getImageUrl()
                mThumbnail = getThumbnail()
                mRefer = getRefer()
                mIsVideo = isVideo()
                mWidth = getWidth()
                mHeight = getHeight()
            }
        }
        bean.toString().logI()
        previewBeans.add(bean)
    }
    val data = ImagePreviewEvent(
        previewBeans as ArrayList<ImagePreviewBean>,
        position,
        needDown,
        isWhiteTheme
    )
    activity.startActivity(Intent(activity, target).apply {
        putExtra(BundleConstant.DATA, data)
    })
}

fun ArrayList<String>.parseImagePreviewBeans():List<ImagePreviewBean>{
    val list = mutableListOf<ImagePreviewBean>()
    forEach {
        list.add(ImagePreviewBean(url = it))
    }
    return list
}

