package com.theone.mvvm.core.ext

import android.app.Activity
import com.theone.mvvm.core.widge.dialog.ProgressDialog
import com.theone.mvvm.entity.ProgressBean

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
 * @date 2021-04-30 16:49
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */


private var progressDialog: ProgressDialog? = null

fun Activity.showProgressDialog(data:ProgressBean){
    if(null == progressDialog){
        progressDialog = ProgressDialog(this)
    }
    progressDialog?.run {
        setMessage(data.msg)
        setProgress(data.percent,data.max)
        if(!isShowing){
            show()
        }
    }
}

fun hideProgressDialog(){
    progressDialog?.dismiss()
    progressDialog = null
}