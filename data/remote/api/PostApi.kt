package com.sample.app.data.remote.api

import com.sample.app.data.remote.models.NetworkPost
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<NetworkPost>
}