package com.theone.mvvm.core.util

import com.effective.android.anchors.task.Task
import com.theone.common.ext.logE
import com.theone.mvvm.core.ext.mAnchors

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
 * @date 2021-04-01 16:44
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTask(isAsyncTask:Boolean): Task(javaClass.simpleName,isAsyncTask) {

    init {
        mAnchors.add(javaClass.name)
        mAnchors.toString().logE()
    }

}