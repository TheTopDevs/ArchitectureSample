package com.sample.app.data.remote.manager.implementation

import com.sample.app.data.models.Comment
import com.sample.app.data.models.Post
import com.sample.app.data.remote.api.PostApi
import com.sample.app.data.remote.manager.PostDataSource
import com.sample.app.data.remote.models.NetworkPost
import com.sample.app.data.remote.utils.NetworkErrorConverterHelper

class PostDataSourceImpl(
    private val postApi: PostApi,
    private val networkErrorConverterHelper: NetworkErrorConverterHelper
) : PostDataSource {

    override suspend fun getPosts(): List<Post> = try {
        val data = postApi.getPosts()
        data.map { NetworkPost.mapToModel(it) }
    } catch (e: Throwable) {
        throw networkErrorConverterHelper.parseError(e)
    }
}