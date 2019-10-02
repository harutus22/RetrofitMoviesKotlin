package com.example.retrofitmovieskotlin.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(val title: String?, val image: String?, val rating: Double, val releaseYear: Int,
            val genre: Array<String>?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.createStringArray()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(image)
        dest?.writeDouble(rating)
        dest?.writeInt(releaseYear)
        dest?.writeStringArray(genre)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (title != other.title) return false
        if (image != other.image) return false
        if (rating != other.rating) return false
        if (releaseYear != other.releaseYear) return false
        if (genre != null) {
            if (other.genre == null) return false
            if (!genre.contentEquals(other.genre)) return false
        } else if (other.genre != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + releaseYear
        result = 31 * result + (genre?.contentHashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}