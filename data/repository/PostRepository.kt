package com.sample.app.data.repository

import com.sample.app.data.Result
import com.sample.app.data.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun observePosts(): Flow<List<Post>>

    fun fetchPosts(): Flow<Result<Unit>>
}