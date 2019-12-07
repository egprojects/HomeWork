package ru.egprojects.androidlab.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_player.view.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.factory.PosterFactory
import ru.egprojects.androidlab.service.IPlayerServiceInterface
import ru.egprojects.androidlab.service.PlayerService
import java.text.SimpleDateFormat
import java.util.*

class PlayerFragment : Fragment() {

    private val timeFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    private val progressHandler = ProgressHandler(this)
    private var playerService: IPlayerServiceInterface? = null
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            playerService = IPlayerServiceInterface.Stub.asInterface(service)
            playerService?.setCurrentAudioIndex(arguments!!.getInt(ARG_AUDIO_INDEX))
            bindView()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            playerService = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments == null) throw IllegalStateException("No data passed to PlayerFragment")
        startPlayerService()
    }

    private fun startPlayerService() {
        val intent = Intent(context, PlayerService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = playerService?.currentAudio?.title
            subtitle = playerService?.currentAudio?.performer
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_player, container, false)?.apply {
        sb_player_seeker.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) playerService?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        tv_player_current_time.text = timeFormat.format(
                Date(0)
        )

        ib_player_close.setOnClickListener { activity?.onBackPressed() }
        ib_player_prev.setOnClickListener {
            playerService?.prev()
            bindView()
        }
        ib_player_play_pause.setOnClickListener {
            if (playerService?.isPlaying == true) {
                playerService?.pause()
                ib_player_play_pause.setImageResource(R.drawable.ic_player_play)
            } else {
                playerService?.play()
                ib_player_play_pause.setImageResource(R.drawable.ic_player_pause)
                progressHandler.sendEmptyMessage(0)
            }
        }
        ib_player_next.setOnClickListener {
            playerService?.next()
            bindView()
        }
        ib_player_stop.setOnClickListener {
            playerService?.stop()
            ib_player_play_pause.setImageResource(R.drawable.ic_player_play)
        }
    }

    private fun bindView() = view?.apply {
        playerService?.also { player ->
            playerService?.currentAudio?.also { audio ->
                (activity as? AppCompatActivity)?.supportActionBar?.apply {
                    title = audio.title
                    subtitle = audio.performer
                }

                iv_player_poster.setImageDrawable(
                        PosterFactory.createRoundedPoster(resources, audio.poster)
                )

                tv_player_title.text = audio.title
                tv_player_performer.text = audio.performer
            }

            sb_player_seeker.progress = 0
            sb_player_seeker.max = player.duration

            tv_player_duration.text = timeFormat.format(
                    Date(player.duration.toLong())
            )
        }
    }

    private fun updatePosition(pos: Int) = view?.apply {
        tv_player_current_time.text = timeFormat.format(
                Date(pos.toLong())
        )
        sb_player_seeker.progress = pos
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.page_title_audios)
            subtitle = null
        }
    }

    companion object {
        private const val ARG_AUDIO_INDEX = "audioIndex"

        fun newInstance(audioNumber: Int) = PlayerFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_AUDIO_INDEX, audioNumber)
            }
        }
    }

    private class ProgressHandler(val fragment: PlayerFragment) : Handler() {
        override fun handleMessage(msg: Message) {
            fragment.playerService?.also {
                fragment.updatePosition(it.position)
                if (it.isPlaying) sendEmptyMessageDelayed(0, 500)
            }
        }
    }

}
