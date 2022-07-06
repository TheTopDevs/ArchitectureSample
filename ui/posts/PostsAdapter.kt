package com.sample.app.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sample.app.base.view.BaseViewHolder
import com.sample.app.data.models.Post
import com.sample.app.databinding.LayoutItemPostBinding

class PostsAdapter(
    private val onPostClicked: (post: Post) -> Unit
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostComparator) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostViewHolder(
            LayoutItemPostBinding.inflate(layoutInflater, parent, false),
            onPostClicked
        )
    }

    inner class PostViewHolder(
        binding: LayoutItemPostBinding,
        val onPostClicked: (post: Post) -> Unit
    ) : BaseViewHolder<LayoutItemPostBinding, Post>(binding) {

        init {
            binding.holder = this
        }

        override fun bind(data: Post, position: Int) {
            binding.post = data
            binding.position = position
        }
    }

    private object PostComparator : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
    }
}