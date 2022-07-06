package com.sample.app.data.repository.implementation

import com.sample.app.data.Result
import com.sample.app.data.local.manager.PostDbManager
import com.sample.app.data.models.Post
import com.sample.app.data.remote.manager.PostDataSource
import com.sample.app.data.repository.PostRepository
import com.sample.app.util.wrapAsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource,
    private val postDbManager: PostDbManager
) : PostRepository {

    override fun observePosts(): Flow<List<Post>> {
        return postDbManager.getAllPosts()
    }

    override fun fetchPosts(): Flow<Result<Unit>> = flow {
        val posts = postDataSource.getPosts()
        postDbManager.deleteAllPosts()
        postDbManager.savePosts(posts)
        emit(Unit)
    }.wrapAsResult()
}