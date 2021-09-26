package com.example.imdb.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie (
@SerializedName("id")
val id: String?,
@SerializedName("title")
val title: String?,
@SerializedName("poster_path")
val poster_path: String?,

@SerializedName("vote_average")
val vote_average: Float?,
@SerializedName("release_date")
val release: String?,


@SerializedName("runtime")
val runtime: Int?,
@SerializedName("overview")
val overview: String?,

@SerializedName("original_language")
val original_language: String?,
) :Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(), parcel.readFloat(),
        parcel.readString(), parcel.readInt(), parcel.readString(),parcel.readString()
    ) {
    }


    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
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