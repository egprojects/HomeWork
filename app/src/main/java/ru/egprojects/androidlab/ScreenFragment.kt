package ru.egprojects.androidlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ScreenFragment(private val screen: Screen) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        val button = view.findViewById<Button>(R.id.button)

        textView.text = context?.getString(R.string.tv_text, screen)
        val nextScreenIndex = if (screen.ordinal < Screen.values().size - 1) {
            screen.ordinal + 1
        } else 0
        val nextScreen = Screen.values()[nextScreenIndex]
        button.text = context?.getString(R.string.button_text, nextScreen)
        button.setOnClickListener { (activity as MainActivity).changeScreen(nextScreen) }

        return view
    }

}