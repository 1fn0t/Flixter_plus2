package com.example.flixter2

import com.google.gson.annotations.SerializedName

class CastMember {
    @SerializedName("id")
    var memberId: String? = null

    @JvmField
    @SerializedName("name")
    var memberName: String? = null


    @SerializedName("file_path")
    var memberPhoto: String? = null

    public fun putPhoto(path: String?) {
        memberPhoto = path
    }
}

