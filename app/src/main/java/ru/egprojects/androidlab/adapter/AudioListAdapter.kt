package ru.egprojects.androidlab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_audio.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.model.Audio

class AudioListAdapter(
        private val data: List<Audio>,
        private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<AudioItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AudioItemHolder
            .create(parent, onClick)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AudioItemHolder, position: Int) =
            holder.bind(data[position])

}

class AudioItemHolder(
        override val containerView: View,
        private val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    fun bind(audio: Audio) {
        iv_item_poster.setImageResource(audio.poster)
        tv_item_title.text = audio.toString()
        tv_item_genres.text = audio.genres

        itemView.setOnClickListener { onClick(adapterPosition) }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (Int) -> Unit): AudioItemHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_audio, parent, false)

            return AudioItemHolder(view, onClick)
        }
    }
}
