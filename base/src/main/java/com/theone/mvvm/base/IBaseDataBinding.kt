package com.theone.mvvm.base

import androidx.annotation.NonNull

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
 * @date 2021-03-31 15:04
 * @describe DataBinding基类相关
 * @email 625805189@qq.com
 * @remark
 */
interface IBaseDataBinding {

    /**
     * 视图绑定里ViewModel的ID
     * @return Int
     */
    fun getBindingVmId():Int

    /**
     * 视图绑定里Click的ID
     * @return Int
     */
    fun getBindingClickId(): Int

    /**
     * 视图绑定里的Click - 需实现TheClick
     * @return TheClick?
     */
    fun getBindingClick(): IClick?

    /**
     * 添加绑定
     * @param variableId Int 绑定的ID
     * @param any Any 绑定的内容
     */
    fun addBindingParams(@NonNull variableId: Int, @NonNull any: Any)

}
