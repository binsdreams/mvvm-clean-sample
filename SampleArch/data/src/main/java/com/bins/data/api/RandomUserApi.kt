package com.bins.data.api

import com.bins.data.entities.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RandomUserApi{

    @GET("top-headlines?country=us")
    fun getRandomUsers(): Deferred<UserResponse>

}