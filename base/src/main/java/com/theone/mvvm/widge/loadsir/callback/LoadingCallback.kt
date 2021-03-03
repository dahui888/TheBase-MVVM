package com.theone.mvvm.widge.loadsir.callback

import android.content.Context
import android.view.View
import com.theone.mvvm.widge.loadsir.callback.Callback
import com.theone.mvvm.R


class LoadingCallback : Callback() {

    override fun onCreateView(): Int  = R.layout.layout_loading

    override fun onReloadEvent(context: Context?, view: View?): Boolean  = false

}