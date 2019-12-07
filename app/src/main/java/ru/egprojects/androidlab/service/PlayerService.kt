package ru.egprojects.androidlab.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import ru.egprojects.androidlab.model.Audio
import ru.egprojects.androidlab.notification.PlayerNotification
import ru.egprojects.androidlab.repository.AudioRepository


class PlayerService : Service() {

    private var audioList: List<Audio> = AudioRepository.data
    private var currentAudioIndex: Int = 0
    private var player: MediaPlayer? = null

    override fun onBind(intent: Intent?) = binder

    private val binder = object : IPlayerServiceInterface.Stub() {

        private var isPlaying = false

        private val onCompletionListener = { _: MediaPlayer -> next() }

        override fun play() {
            player?.start()
            isPlaying = true
            val notification = PlayerNotification.create(
                    this@PlayerService, audioList[currentAudioIndex], currentAudioIndex
            )
            startForeground(1, notification)
        }

        override fun pause() {
            player?.pause()
            isPlaying = false
        }

        override fun stop() {
            player?.release()
            player = null
            isPlaying = false
            setCurrentAudioIndex(currentAudioIndex)
        }

        override fun setCurrentAudioIndex(index: Int) {
            currentAudioIndex = index
            player?.release()
            player = MediaPlayer.create(
                    this@PlayerService, audioList[currentAudioIndex].src
            )
            if (isPlaying) play()
            player?.setOnCompletionListener(onCompletionListener)
        }

        override fun getCurrentAudio() = audioList[currentAudioIndex]

        override fun prev() {
            var audioIndex = currentAudioIndex - 1
            if (audioIndex < 0) audioIndex = audioList.size - 1

            setCurrentAudioIndex(audioIndex)
        }

        override fun next() {
            var audioIndex = currentAudioIndex + 1
            if (audioIndex >= audioList.size) audioIndex = 0

            setCurrentAudioIndex(audioIndex)
        }

        override fun isPlaying() = isPlaying

        override fun getPosition() = player?.currentPosition ?: 0

        override fun seekTo(pos: Int) {
            player?.seekTo(pos)
        }

        override fun getDuration() = player?.duration ?: 0

        override fun release() {
            player?.release()
            player = null
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binder.release()
    }

}