package ru.egprojects.androidlab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.activity_anime.*

class AnimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)

        val anime = Api.loadAnime(intent.getIntExtra(EXTRA_ANIME_ID, -1))
        Glide.with(iv_anime_poster)
                .load(anime.posterSrc)
                .override(iv_anime_poster.width, iv_anime_poster.height)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv_anime_poster)
        setSupportActionBar(toolbar_anime)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = anime.title
        tv_anime_genre.text = anime.genre
    }

    companion object {
        private const val EXTRA_ANIME_ID = "animeId"

        fun start(activity: AppCompatActivity, animeId: Int) {
            val intent = Intent(activity.baseContext, AnimeActivity::class.java)
            intent.putExtra(EXTRA_ANIME_ID, animeId)
            activity.startActivity(intent)
        }
    }
}
