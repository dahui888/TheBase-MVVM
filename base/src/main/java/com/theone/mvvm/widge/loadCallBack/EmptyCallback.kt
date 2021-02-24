package com.theone.mvvm.widge.loadCallBack


import android.content.Context
import android.view.View
import android.widget.ImageView
import com.kingja.loadsir.callback.Callback
import com.theone.mvvm.R


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

    override fun onViewCreate(context: Context?, view: View?) {
        val ivStatus = view?.findViewById<ImageView>(R.id.stateImageView)
        ivStatus?.setImageResource(R.drawable.status_search_result_empty)
    }

}