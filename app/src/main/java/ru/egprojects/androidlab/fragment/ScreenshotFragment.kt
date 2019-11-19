package ru.egprojects.androidlab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_screenshot.view.*
import ru.egprojects.androidlab.R

class ScreenshotFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_screenshot, container, false).apply {
            val src = arguments?.getString(ARG_SRC)
            Glide.with(iv_screenshot)
                    .load(src)
                    .override(iv_screenshot.width, iv_screenshot.height)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iv_screenshot)
        }
    }

    companion object {
        private const val ARG_SRC = "src"

        fun newInstance(src: String) = ScreenshotFragment().apply {
            arguments = Bundle().apply { putString(ARG_SRC, src) }
        }
    }

}