package com.quxiangtech.kotlin

import android.app.Dialog

class LoadingDialog {

    private var dialog: Dialog? = null

    fun show() {
        dialog?.show()
    }

    fun cancel() {
        dialog?.dismiss()
    }
}