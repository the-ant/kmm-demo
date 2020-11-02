package com.sdt.kmm.demoapp.shared

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val uuid: String = "Android UUID: ${java.util.UUID.randomUUID().toString()}"
}