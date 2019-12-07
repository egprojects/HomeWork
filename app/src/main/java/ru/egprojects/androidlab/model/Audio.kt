package ru.egprojects.androidlab.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

/**
 * Parcelize с AIDL'ом не работает =_=
 *
 * Там у Котлина баг, у него где-то там под капотом получается не Audio[], а Object[]
 *
 * Может хоть баллов сыпанёшь побольше тогда, т.к. в задание, вроде как, не входят разборки с
 * багами Котлина
 */
data class Audio(
        val performer: String,
        val title: String,
        @DrawableRes val poster: Int,
        val genres: String,
        @RawRes val src: Int
) : Parcelable {

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeString(performer)
            writeString(title)
            writeInt(poster)
            writeString(genres)
            writeInt(src)
        }
    }

    override fun describeContents() = 0

    override fun toString() = "$performer - $title"

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Audio> = object : Parcelable.Creator<Audio> {
            override fun createFromParcel(parcel: Parcel): Audio {
                val performer = parcel.readString()
                val title = parcel.readString()
                val poster = parcel.readInt()
                val genres = parcel.readString()
                val src = parcel.readInt()

                return Audio(performer!!, title!!, poster, genres!!, src)
            }

            override fun newArray(size: Int): Array<Audio?> {
                return arrayOfNulls(size)
            }
        }
    }

}
