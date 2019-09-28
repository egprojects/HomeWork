package ru.egprojects.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeScreen(Screen.A)
    }

    fun changeScreen(screen: Screen) {
        val fragment = ScreenFragment(screen)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(screen.toString())
            .commit()
    }
}
