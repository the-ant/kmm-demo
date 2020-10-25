package com.sdt.kmm.demoapp.shared

import android.util.Log


actual fun printlnLog(TAG: String, message: String, logLevel: LogLevel) {
    when(logLevel) {
        LogLevel.DEBUG -> Log.d(TAG, message)
        LogLevel.WARN -> Log.w(TAG, message)
        LogLevel.INFO -> Log.i(TAG, message)
        LogLevel.ERROR -> Log.e(TAG, message)
    }
}
