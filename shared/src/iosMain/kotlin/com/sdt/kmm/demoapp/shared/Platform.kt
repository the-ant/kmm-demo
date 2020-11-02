package com.sdt.kmm.demoapp.shared

import platform.UIKit.UIDevice
import platform.Foundation.NSUUID

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val uuid: String = "IOS UUID" + " " + NSUUID().UUIDString()
}
