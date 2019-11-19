package ru.egprojects.androidlab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_anime.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.api.Api
import ru.egprojects.androidlab.model.Anime

class AnimeAdapter(
        private var dataSource: List<Anime>
) : RecyclerView.Adapter<AnimeItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeItemHolder
            .create(parent, this)

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: AnimeItemHolder, position: Int) =
            holder.bind(dataSource[position])

}

class AnimeItemHolder(
        override val containerView: View,
        private val adapter: AnimeAdapter
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(anime: Anime) {
        tv_title.text = anime.title
        tv_genre.text = anime.genre

        Glide.with(iv_poster)
                .load(anime.posterSrc)
                .override(iv_poster.width, iv_poster.height)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv_poster)

        btn_remove.setOnClickListener {
            Api.remove(adapterPosition)
            adapter.notifyItemRemoved(adapterPosition)
        }
    }

    companion object {
        fun create(parent: ViewGroup, adapter: AnimeAdapter): AnimeItemHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_anime, parent, false)

            return AnimeItemHolder(view, adapter)
        }
    }

}
