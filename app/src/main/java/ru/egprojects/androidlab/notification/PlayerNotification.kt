package ru.egprojects.androidlab.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.activity.MainActivity
import ru.egprojects.androidlab.factory.PosterFactory
import ru.egprojects.androidlab.model.Audio


object PlayerNotification {

    private const val CHANNEL_ID = "ru.egprojects.androidlab.audio_player"
    private const val ACTION_CLOSE = "Close"
    private const val ACTION_PREV = "Previous"
    private const val ACTION_PLAY_PAUSE = "Play/pause"
    private const val ACTION_NEXT = "Next"
    private const val ACTION_STOP = "Stop"

    fun create(context: Context, audio: Audio, audioIndex: Int): Notification {
        val manager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.player_notification_channel_name)
            val descriptionText = context.getString(R.string.player_notification_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = manager.getNotificationChannel(CHANNEL_ID)
                    ?: NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = descriptionText
            manager.createNotificationChannel(channel)
        }

        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(MainActivity.EXTRA_CURRENT_AUDIO_INDEX, audioIndex)
        }
        val intent = PendingIntent.getActivity(context, 0, activityIntent, 0)


        return NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_player_play)
                .setContentTitle(audio.title)
                .setContentText(audio.performer)
                .setStyle(MediaStyle())
                .setLargeIcon(
                        PosterFactory.createBitmapPoster(context.resources, audio.poster)
                )
                .addAction(R.drawable.ic_player_close, ACTION_CLOSE, null)
                .addAction(R.drawable.ic_player_prev, ACTION_PREV, null)
                .addAction(R.drawable.ic_player_play, ACTION_PLAY_PAUSE, null)
                .addAction(R.drawable.ic_player_next, ACTION_NEXT, null)
                .addAction(R.drawable.ic_player_stop, ACTION_STOP, null)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(intent)
                .build()
    }
}
