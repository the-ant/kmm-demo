package com.sdt.kmm.demoapp.shared


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    fun randomUUID(): String {
        return Platform().uuid
    }
}
