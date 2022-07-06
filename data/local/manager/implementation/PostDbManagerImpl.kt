package com.sample.app.data.local.manager.implementation

import com.sample.app.data.local.dao.PostsDao
import com.sample.app.data.local.manager.PostDbManager
import com.sample.app.data.local.models.DbPost
import com.sample.app.data.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostDbManagerImpl(private val postsDao: PostsDao) : PostDbManager {

    override suspend fun savePosts(posts: List<Post>) {
        val dbPosts = posts.map { DbPost.createFromModel(it) }
        postsDao.insertAll(dbPosts)
    }

    override fun getAllPosts(): Flow<List<Post>> {
        return postsDao.getAll().map { dbPosts ->
            dbPosts.map { dbPost -> DbPost.mapToModel(dbPost) }
        }
    }

    override suspend fun deleteAllPosts() {
        postsDao.deleteAll()
    }
}