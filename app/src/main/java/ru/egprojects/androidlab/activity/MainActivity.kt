package ru.egprojects.androidlab.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.fragment.AnimesFragment
import ru.egprojects.androidlab.fragment.HomeFragment
import ru.egprojects.androidlab.fragment.ScreenshotsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_main, HomeFragment())
                .commit()

        nav_view_main.setOnNavigationItemSelectedListener {
            title = it.title
            val fragment = when (it.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_animes -> AnimesFragment()
                else -> ScreenshotsFragment()
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_main, fragment)
                    .commit()

            true
        }
    }
}
