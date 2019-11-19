package ru.egprojects.androidlab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_screenshots.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.model.Anime


class ScreenshotsAdapter(
        private val dataSource: List<Anime>,
        private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<ScreenshotsItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScreenshotsItemHolder
            .create(parent, fragmentManager)

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: ScreenshotsItemHolder, position: Int) =
            holder.bind(dataSource[position])

}

class ScreenshotsItemHolder(
        override val containerView: View,
        private val fragmentManager: FragmentManager
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(anime: Anime) {
        tv_screenshots_title.text = anime.title
        tv_screenshots_subtitle.text = anime.genre

        val options = RequestOptions()
                .override(iv_screenshots_poster.width, iv_screenshots_poster.height)
                .transform(CircleCrop())
        Glide.with(iv_screenshots_poster)
                .load(anime.posterSrc)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv_screenshots_poster)

        val screenshotsSrc = anime.screenshotsSrc
        if (screenshotsSrc != null) {
            vp_screenshots.adapter = ScreenshotPagerAdapter(screenshotsSrc, fragmentManager)
        }

        tv_screenshots_desc.text = anime.description
    }

    companion object {
        fun create(parent: ViewGroup, fm: FragmentManager): ScreenshotsItemHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_screenshots, parent, false)

            return ScreenshotsItemHolder(view, fm)
        }
    }

}
