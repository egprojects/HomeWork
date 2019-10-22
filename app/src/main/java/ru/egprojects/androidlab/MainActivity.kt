package ru.egprojects.androidlab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE_SHARE = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_main_share.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, edittext_main_msg.text.toString())
                type = "text/plain"
            }
            startActivityForResult(sendIntent, REQUEST_CODE_SHARE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val isDone = requestCode == REQUEST_CODE_SHARE && resultCode == Activity.RESULT_OK
        Toast.makeText(
                baseContext,
                if (isDone) R.string.main_toast_done else R.string.main_toast_failed,
                Toast.LENGTH_SHORT
        ).show()
    }

}
