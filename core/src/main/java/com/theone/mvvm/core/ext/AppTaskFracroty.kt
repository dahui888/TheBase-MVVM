package com.theone.mvvm.core.ext

import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.task.Task
import com.effective.android.anchors.task.TaskCreator
import com.effective.android.anchors.task.project.Project
import com.theone.common.ext.LogInit
import com.theone.common.ext.logE
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.core.util.BaseTask

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
 * @date 2021-04-01 16:42
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

val mAnchors: MutableList<String> = arrayListOf()

class AnchorsInitUtil {

    fun init() {
        //设置锚点
        with(AnchorsManager.getInstance()) {
            debuggable(BaseApplication.DEBUG)
            for (id in mAnchors) {
                "111 $id".logE()
            }
            addAnchor("InitLoadSirTask")
            start(
                Project.Builder("app", Project.TaskFactory(TaskCreator)).apply {
                    for (id in mAnchors) {
                        "222 $id".logE()
                    }
                    add("InitLoadSirTask")
                }.build()
            )
        }
    }
}

object TaskCreator : TaskCreator {
    override fun createTask(taskName: String): Task {
        taskName.logE()
        return InitLoadSirTask()
    }
}

class InitLoadSirTask : BaseTask(true) {

    override fun run(name: String) {
        initLoadSir()
    }

}