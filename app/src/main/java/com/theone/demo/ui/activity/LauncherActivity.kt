package com.theone.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.arch.QMUILatestVisit

class LauncherActivity : QMUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        var intent = QMUILatestVisit.intentOfLatestVisit(this)
        if (intent == null) {
            intent = Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}