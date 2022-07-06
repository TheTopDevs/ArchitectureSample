package com.sample.app.data.remote.models

import com.sample.app.data.models.Post
import com.google.gson.annotations.SerializedName

data class NetworkPost(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
) {
    companion object {
        fun mapToModel(networkPost: NetworkPost): Post = Post(
            id = networkPost.id,
            title = networkPost.title,
            body = networkPost.body
        )
    }
}