package com.sdt.kmm.demoapp.androidApp

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sdt.kmm.demoapp.androidApp.databinding.ActivitySignUpBinding
import com.sdt.kmm.demoapp.shared.Api
import com.sdt.kmm.demoapp.shared.logInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    override fun ActivitySignUpBinding.init() {
        ivBack.setOnClickListener { finish() }

        ivSignUp.setOnClickListener { signUp() }

        tvSignIn.setOnClickListener { finish() }
    }

    private fun ActivitySignUpBinding.signUp() = lifecycleScope.launch {
        showProcess()
        delay(1000)
        try {
            Api().register(
                edtUserName.text.toString().trim(),
                edtPassword.text.toString().trim(),
                edtDisplayName.text.toString().trim()
            ) { isSucceeded ->
                GlobalScope.launch(Dispatchers.Main) {
                    if (isSucceeded) {
                        logInfo("Api", "signUp: $isSucceeded")
                        Toast.makeText(
                            this@SignUpActivity,
                            "Registration Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Registration Failed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    dismissProcess()
                }
            }
        } catch (e: Exception) {
            dismissProcess()
            e.printStackTrace()
        }
    }

    override fun onStop() {
        binding.edtDisplayName.hideSoftKeyboard()
        binding.edtPassword.hideSoftKeyboard()
        binding.edtUserName.hideSoftKeyboard()
        super.onStop()
    }

}