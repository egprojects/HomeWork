package ru.egprojects.androidlab.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.egprojects.androidlab.fragment.ScreenshotFragment

class ScreenshotPagerAdapter(
        private val dataSources: List<String>, fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = ScreenshotFragment.newInstance(dataSources[position])

    override fun getCount() = dataSources.size

}
