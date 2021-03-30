package com.theone.demo.viewmodel

import com.theone.mvvm.base.viewmodel.BaseViewModel
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
 * @date 2021-03-30 17:10
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TestViewModel:BaseViewModel() {

    val cover = StringObservableField("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2586870947,764155106&fm=26&gp=0.jpg")

}