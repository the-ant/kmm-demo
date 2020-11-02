package com.sdt.kmm.demoapp.androidApp

import com.sdt.kmm.demoapp.androidApp.databinding.ActivityMainBinding
import com.sdt.kmm.demoapp.shared.Greeting


fun greet(): String {
    return Greeting().greeting()
}

fun randomUUID(): String {
    return Greeting().randomUUID()
}

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    override fun ActivityMainBinding.init() {
        tvPlatform.text = greet()
        tvUUID.text = randomUUID()

        intent?.apply {
            tvUsername.text = getStringExtra("username")
            tvDisplayName.text = getStringExtra("display_name")
        }
    }

}
