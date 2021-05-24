package com.theone.mvvm.core.ext

import com.effective.android.anchors.task.Task

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

val mTasks: MutableList<Class<*>> = arrayListOf()

//object TaskFactory{
//
//    fun initAnchors(vararg tasks:Class<*>):TaskFactory{
//        mTasks.add(InitLoadSirTask::class.java)
//        mTasks.addAll(tasks)
//        return this
//    }
//
//    fun init(task:TaskCreator){
//        if(mTasks.isEmpty()){
//          throw RuntimeException("Please initAnchors first.")
//        }
//        with(AnchorsManager.getInstance()) {
//            debuggable(BaseApplication.DEBUG)
//            for (task in mTasks){
//                addAnchor(task.simpleName)
//            }
//            start(
//                Project.Builder("app", Project.TaskFactory(task)).apply {
//                    for (task in mTasks){
//                        add(task.simpleName)
//                    }
//                }.build()
//            )
//        }
//    }
//}

class InitDefault : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "0"
    }

    override fun run(name: String) {

    }
}

class InitLoadSirTask : Task(TASK_ID,true) {

    companion object{
        const val TASK_ID = "0"
    }

    override fun run(name: String) {
        initLoadSir()
    }

}