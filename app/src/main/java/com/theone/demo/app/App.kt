package com.theone.demo.app

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.task.Task
import com.effective.android.anchors.task.project.Project
import com.tencent.mmkv.MMKV
import com.theone.demo.BuildConfig
import com.theone.demo.app.util.RxHttpManager
import com.theone.demo.ui.activity.ErrorActivity
import com.theone.demo.ui.activity.LauncherActivity
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.base.appContext
import com.theone.mvvm.core.CoreApplication
import com.theone.mvvm.core.ext.InitLoadSirTask
import rxhttp.wrapper.param.RxHttp


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
class App : CoreApplication() {

    override fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun init(application: Application) {
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        //防止项目崩溃，崩溃后打开错误界面
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
            .enabled(true)//是否启用CustomActivityOnCrash崩溃拦截机制 必须启用！不然集成这个库干啥？？？
            .showErrorDetails(false) //是否必须显示包含错误详细信息的按钮 default: true
            .showRestartButton(false) //是否必须显示“重新启动应用程序”按钮或“关闭应用程序”按钮default: true
            .logErrorOnRestart(false) //是否必须重新堆栈堆栈跟踪 default: true
            .trackActivities(true) //是否必须跟踪用户访问的活动及其生命周期调用 default: false
            .minTimeBetweenCrashesMs(2000) //应用程序崩溃之间必须经过的时间 default: 3000
            .restartActivity(LauncherActivity::class.java) // 重启的activity
            .errorActivity(ErrorActivity::class.java)
            .apply()
        super.init(application)
        RxHttp.init(
            RxHttpManager.getHttpClient(
                RxHttpManager.HttpBuilder().setNeedCookie(true)
            ), BaseApplication.DEBUG
        )
    }

    override fun onMainProcessInit() {
        with(AnchorsManager.getInstance()) {
            debuggable(DEBUG)
            addAnchor(
                InitCrashTask.TASK_ID,
                InitNetTask.TASK_ID,
                InitCommonTask.TASK_ID,
                InitLoadSirTask.TASK_ID
            )
            start(
                Project.Builder("app", Project.TaskFactory(TaskCreator)).apply {
                    add(InitCrashTask.TASK_ID)
                    add(InitNetTask.TASK_ID)
                    add(InitCommonTask.TASK_ID)
                    add(InitLoadSirTask.TASK_ID)
                }.build()
            )
        }
    }

}


object TaskCreator : com.effective.android.anchors.task.TaskCreator {
    override fun createTask(taskName: String): Task {
        return when (taskName) {
            InitCrashTask.TASK_ID -> InitNetTask()
            InitNetTask.TASK_ID -> InitNetTask()
            InitCommonTask.TASK_ID -> InitCommonTask()
            else -> InitLoadSirTask()
        }
//        return when (taskName) {
//            InitCrashTask::class.java.simpleName -> InitNetTask()
//            InitNetTask::class.java.simpleName -> InitNetTask()
//            InitCommonTask::class.java.simpleName -> InitCommonTask()
//            else -> InitLoadSirTask()
//        }
    }
}

class InitCrashTask : Task(TASK_ID, false) {

    companion object {
        const val TASK_ID = "1"
    }

    override fun run(name: String) {
        //防止项目崩溃，崩溃后打开错误界面
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
            .enabled(true)//是否启用CustomActivityOnCrash崩溃拦截机制 必须启用！不然集成这个库干啥？？？
            .showErrorDetails(false) //是否必须显示包含错误详细信息的按钮 default: true
            .showRestartButton(false) //是否必须显示“重新启动应用程序”按钮或“关闭应用程序”按钮default: true
            .logErrorOnRestart(false) //是否必须重新堆栈堆栈跟踪 default: true
            .trackActivities(true) //是否必须跟踪用户访问的活动及其生命周期调用 default: false
            .minTimeBetweenCrashesMs(2000) //应用程序崩溃之间必须经过的时间 default: 3000
            .restartActivity(LauncherActivity::class.java) // 重启的activity
            .errorActivity(ErrorActivity::class.java)
            .apply()
    }

}

class InitNetTask : Task(TASK_ID, true) {

    companion object {
        const val TASK_ID = "2"
    }

    override fun run(name: String) {
        RxHttp.init(
            RxHttpManager.getHttpClient(
                RxHttpManager.HttpBuilder().setNeedCookie(true)
            ), BaseApplication.DEBUG
        )
    }

}

class InitCommonTask : Task(TASK_ID, true) {

    companion object {
        const val TASK_ID = "3"
    }

    override fun run(name: String) {
        MMKV.initialize(appContext.filesDir.absolutePath + "/mmkv")
    }
}

