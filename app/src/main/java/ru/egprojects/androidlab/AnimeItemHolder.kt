package ru.egprojects.androidlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_anime.*

class AnimeItemHolder(
        override val containerView: View,
        private val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(anime: Anime) {
        tv_title.text = anime.title
        tv_genre.text = anime.genre

        Glide.with(iv_poster)
                .load(anime.posterSrc)
                .override(iv_poster.width, iv_poster.height)
                .centerCrop()
                .transition(withCrossFade())
                .into(iv_poster)

        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (Int) -> Unit): AnimeItemHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_anime, parent, false)

            return AnimeItemHolder(view, onClick)
        }
    }
}
