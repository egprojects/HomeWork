package ru.egprojects.androidlab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_screenshots.view.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.adapter.ScreenshotsAdapter
import ru.egprojects.androidlab.api.Api

class ScreenshotsFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_screenshots, container, false).apply {
            rv_screenshots.layoutManager = LinearLayoutManager(context)
            rv_screenshots.adapter = ScreenshotsAdapter(Api.loadAnimes(), childFragmentManager)
        }
    }
}