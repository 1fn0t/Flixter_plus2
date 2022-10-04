package com.example.flixster

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Show (
    @SerializedName("id")
    var showId: String? = null,

    @JvmField
    @SerializedName("name")
    var showName: String? = null,

    @JvmField
    @SerializedName("vote_average")
    var showAverage: String? = null,

    @SerializedName("poster_path")
    var showPoster: String? = null) : java.io.Serializable{}