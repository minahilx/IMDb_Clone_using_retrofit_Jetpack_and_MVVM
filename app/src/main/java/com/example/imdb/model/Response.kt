package com.example.imdb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Response (


    @SerializedName("results")
    val movie: List<Movie>
        ):Parcelable{

constructor():this(mutableListOf())


}