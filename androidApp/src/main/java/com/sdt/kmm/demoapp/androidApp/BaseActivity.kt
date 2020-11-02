package com.sdt.kmm.demoapp.androidApp

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseActivity<B : ViewDataBinding> constructor(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

    lateinit var binding: B
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, layoutId)
        progressDialog = ProgressDialog.create(this, this)
        binding.init()
    }

    protected abstract fun B.init()

    private fun Activity?.changeStatusBar(
        fullLayout: Boolean = false,
        hideNav: Boolean = false,
        lightBackground: Boolean = false,
        @ColorInt color: Int = Color.TRANSPARENT
    ) {
        val window = this?.window ?: return
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE.or(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            if (fullLayout) {
                decorView.systemUiVisibility = decorView.systemUiVisibility
                    .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            }
            if (hideNav) {
                decorView.systemUiVisibility = decorView.systemUiVisibility
                    .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                    .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            }
            if (lightBackground) {
                decorView.systemUiVisibility = decorView.systemUiVisibility
                    .or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            statusBarColor = color
        }
    }

    fun showProcess() {
        if (progressDialog.isShowing) return
        progressDialog.show()
    }

    fun dismissProcess() {
        progressDialog.dismiss()
    }

    fun View?.hideSoftKeyboard() {
        this ?: return
        clearFocus()
        val inputMethod =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(windowToken, 0)
    }

}
