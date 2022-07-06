package com.sample.app.data.local.manager

import com.sample.app.data.models.Post
import kotlinx.coroutines.flow.Flow

interface PostDbManager {
    suspend fun savePosts(posts: List<Post>)

    fun getAllPosts(): Flow<List<Post>>

    suspend fun deleteAllPosts()
}