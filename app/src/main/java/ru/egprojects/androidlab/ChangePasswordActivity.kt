package ru.egprojects.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        changePasswordButton.setOnClickListener {
            if (passwordField1.text.toString() == passwordField2.text.toString()) {
                PasswordRepository.password = passwordField1.text.toString()
                onBackPressed()
            } else setPasswordError()
        }
    }

    private fun setPasswordError() {
        passwordLayout2.error = getString(R.string.validate_password)
    }
}
