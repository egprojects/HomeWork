package ru.egprojects.androidlab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_audio_list.view.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.adapter.AudioListAdapter
import ru.egprojects.androidlab.repository.AudioRepository

class AudioListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_audio_list, container, false)?.apply {
        rv_audio_list.layoutManager = LinearLayoutManager(context)
        rv_audio_list.adapter = AudioListAdapter(AudioRepository.data) { audioIndex ->
            activity?.also {
                val fragment = PlayerFragment.newInstance(audioIndex)
                it.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
            }
        }
    }

}
