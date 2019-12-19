package com.bins.data.entities

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("large") var large: String? = null,
    @SerializedName("medium") var medium: String? = null
)