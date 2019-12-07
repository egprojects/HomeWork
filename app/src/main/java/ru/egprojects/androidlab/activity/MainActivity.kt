package ru.egprojects.androidlab.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.fragment.AudioListFragment
import ru.egprojects.androidlab.fragment.PlayerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentAudioIndex = intent.getIntExtra(EXTRA_CURRENT_AUDIO_INDEX, -1)
        val fragment = if (currentAudioIndex == -1) {
            title = getString(R.string.page_title_audios)
            AudioListFragment()
        } else PlayerFragment.newInstance(currentAudioIndex)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_CURRENT_AUDIO_INDEX = "currentAudioIndex"
    }

}
