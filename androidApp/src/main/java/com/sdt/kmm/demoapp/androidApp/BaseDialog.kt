package com.sdt.kmm.demoapp.androidApp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseDialog<B : ViewDataBinding>(
    context: Context,
    private val lifecycleOwner: LifecycleOwner? = null,
    @LayoutRes private val layoutId: Int,
    @StyleRes styleId: Int = R.style.SimpleDialogTheme
) : Dialog(context, styleId), LifecycleObserver {

    protected lateinit var mBinding: B

    abstract fun B.initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId, null, false
        )
        setContentView(mBinding.root)

        lifecycleOwner?.lifecycle?.addObserver(this)

        setOnDismissListener {
            lifecycleOwner?.lifecycle?.removeObserver(this)
        }

        mBinding.initViews()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dismissDialog() {
        dismiss()
    }

    fun setWrapContentHeight() {
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun Window?.changeStatusBar(
        fullLayout: Boolean = false,
        hideNav: Boolean = false,
        lightBackground: Boolean = false,
        @ColorInt color: Int = Color.TRANSPARENT
    ) {
        val window = this ?: return
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

    fun Window?.changeStatusBar(
        systemUiVisibility: Int? = null,
        @ColorInt color: Int = Color.TRANSPARENT
    ) {
        val window = this ?: return
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            systemUiVisibility?.let { decorView.systemUiVisibility = it }
            statusBarColor = color
        }
    }

}
