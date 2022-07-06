package com.sample.app.data.remote.manager

import com.sample.app.data.models.Post

interface PostDataSource {

    suspend fun getPosts(): List<Post>
}