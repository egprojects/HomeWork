package ru.egprojects.androidlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        passwordField.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passwordLayout.error = null
                passwordLayout.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        signInButton.setOnClickListener {
            if (passwordField.text.toString() == PasswordRepository.password) {
                startActivity(Intent(this, MainActivity::class.java))
            } else setPasswordError()
        }
    }

    private fun setPasswordError() {
        passwordLayout.error = getString(R.string.validate_password)
    }
}
