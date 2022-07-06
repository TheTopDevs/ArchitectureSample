package com.sample.app.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.app.data.models.Post

@Entity(tableName = DbPost.TABLE_NAME)
data class DbPost(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_BODY)
    val body: String
) {
    companion object {
        const val TABLE_NAME = "posts"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_BODY = "body"

        fun createFromModel(post: Post): DbPost = DbPost(
            id = post.id,
            title = post.title,
            body = post.body
        )

        fun mapToModel(dbPost: DbPost): Post = Post(
            id = dbPost.id,
            title = dbPost.title,
            body = dbPost.body
        )
    }
}