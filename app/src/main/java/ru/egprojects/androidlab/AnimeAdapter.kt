package ru.egprojects.androidlab

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter(
        private var dataSource: List<Anime>,
        private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<AnimeItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeItemHolder =
            AnimeItemHolder.create(parent, onClick)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: AnimeItemHolder, position: Int) =
            holder.bind(dataSource[position])
}
