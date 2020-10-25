package com.sdt.kmm.demoapp.shared


actual fun printlnLog(TAG: String, message: String, logLevel: LogLevel) {
    println("$TAG - $logLevel: $message")
}
