package com.theone.mvvm.core.viewmodel

import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.callback.databind.BooleanObservableField
import com.theone.mvvm.callback.databind.FloatObservableField
import com.theone.mvvm.callback.databind.StringObservableField

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
 * @date 2021-05-08 15:08
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class AppUpdateViewModel:BaseViewModel() {

    val updateInfo:StringObservableField = StringObservableField()
    val verCode:StringObservableField = StringObservableField()
    val isForce:BooleanObservableField = BooleanObservableField()


}