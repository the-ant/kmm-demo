package com.sdt.kmm.demoapp.androidApp

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import com.sdt.kmm.demoapp.androidApp.databinding.DialogProgressBinding


class ProgressDialog private constructor(
    context: Context,
    lifecycleOwner: LifecycleOwner? = null
) : BaseDialog<DialogProgressBinding>(
    context,
    lifecycleOwner,
    R.layout.dialog_progress,
    R.style.ProgressDialogTheme
) {

    companion object {
        fun create(context: Context, lifecycleOwner: LifecycleOwner?): ProgressDialog {
            return ProgressDialog(context, lifecycleOwner)
        }
    }

    @DrawableRes
    private var background: Int = R.drawable.background_progress_dialog
    fun background(@DrawableRes res: Int) = apply {
        this.background = res
    }

    @ColorInt
    private var progressBarColor: Int = Color.WHITE
    fun progressBarColor(@ColorInt color: Int) = apply {
        this.progressBarColor = color
    }

    override fun DialogProgressBinding.initViews() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        window?.apply {
            setGravity(Gravity.CENTER)
            setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        container.setBackgroundResource(background)
        loadingView.setColor(progressBarColor)

    }

    fun show(systemUiVisibility: Int? = null, @ColorInt color: Int = Color.TRANSPARENT) {
        window?.changeStatusBar(systemUiVisibility, color)
        show()
    }

}
