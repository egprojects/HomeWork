package ru.egprojects.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GainingOver().interact(Character.MORGAUSE, Character.MORGAN)
        Character.MORGAN.becomeAVillain()
        logE("Morgause has lured Morgan to her side and Morgan became a villain")
        Killing().interact(Character.MORGAN, Character.UTER)
        logE("Morgan killed Uter")
        GainingOver().interact(Character.MERLIN, Character.MORGAN)
        logE("Merlin brought Morgan back to the true path")
        Character.ARTHUR.becomeAKing()
        logE("Arthur became a king")
    }

    inline fun logE(msg: String) {
        Log.e("MainActivity", msg)
    }

}
