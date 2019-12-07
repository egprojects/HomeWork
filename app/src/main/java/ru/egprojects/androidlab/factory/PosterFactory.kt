package ru.egprojects.androidlab.factory

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

object PosterFactory {

    fun createBitmapPoster(resources: Resources, @DrawableRes resId: Int): Bitmap = BitmapFactory
            .decodeResource(resources, resId)

    fun createRoundedPoster(resources: Resources, @DrawableRes resId: Int): RoundedBitmapDrawable {
        val bitmapSrc = createBitmapPoster(resources, resId)

        return RoundedBitmapDrawableFactory.create(resources, bitmapSrc).apply {
            cornerRadius = 2 * bitmapSrc.width / 100f
        }
    }

}
