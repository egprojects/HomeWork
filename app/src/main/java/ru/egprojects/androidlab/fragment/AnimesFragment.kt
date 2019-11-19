package ru.egprojects.androidlab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_animes.view.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.adapter.AnimeAdapter
import ru.egprojects.androidlab.api.Api
import ru.egprojects.androidlab.model.Anime

class AnimesFragment : Fragment() {

    private lateinit var adapter: AnimeAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animes, container, false).apply {
            adapter = AnimeAdapter(Api.loadAnimes())
            rv_animes.layoutManager = LinearLayoutManager(context)
            rv_animes.adapter = adapter
            fab_animes.setOnClickListener {
                val transaction = childFragmentManager.beginTransaction()
                val prev = childFragmentManager.findFragmentByTag("dialog")
                if (prev != null) transaction.remove(prev)
                transaction.addToBackStack(null)
                DialogFragment(object : DialogFragment.DialogCallback {
                    override fun onSubmit(
                            pos: Int, title: String, genre: String, posterUrl: String, desc: String
                    ) {
                        val anime = Anime(title, posterUrl, genre, desc)
                        Api.add(pos, anime)
                        adapter.notifyItemInserted(pos)
                    }
                }).show(transaction, "dialog")
            }
        }
    }

}
