package com.sdt.kmm.demoapp.androidApp

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sdt.kmm.demoapp.androidApp.databinding.ActivitySignInBinding
import com.sdt.kmm.demoapp.shared.Api
import com.sdt.kmm.demoapp.shared.LoginData
import com.sdt.kmm.demoapp.shared.logInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun ActivitySignInBinding.init() {
        ivBack.setOnClickListener { finish() }

        ivSignIn.setOnClickListener { signIn() }

        tvSignUp.setOnClickListener { toSignUp() }
    }

    private fun ActivitySignInBinding.signIn() = lifecycleScope.launch {
        showProcess()
        delay(1000)
        try {
            Api().login(
                edtUserName.text.toString().trim(),
                edtPassword.text.toString().trim()
            ) { data ->
                logInfo("Api", "login: $data")
                GlobalScope.launch(Dispatchers.Main) {
                    if (data != null) {
                        Toast.makeText(
                            this@SignInActivity,
                            "Login Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        toMain(data)
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Login Failed!",
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
        binding.edtPassword.hideSoftKeyboard()
        binding.edtUserName.hideSoftKeyboard()
        super.onStop()
    }

    private fun toMain(data: LoginData) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", data.user.userName)
        intent.putExtra("display_name", data.user.displayName)
        startActivity(intent)
        finish()
    }

    private fun toSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}
