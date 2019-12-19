package com.bins.data.entities

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("street") var street: Street? = null

)