package com.sample.app.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sample.app.R
import com.sample.app.base.BaseFragmentViewModel
import com.sample.app.data.Result
import com.sample.app.databinding.FragmentPostsBinding
import com.sample.app.util.navigate
import com.sample.app.util.nonNullValue
import com.sample.app.viewmodel.PostsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : BaseFragmentViewModel<FragmentPostsBinding, PostsViewModel>() {

    override val viewModel: PostsViewModel by viewModels()

    override fun getFragmentLayoutRedId(): Int = R.layout.fragment_posts

    private lateinit var postsAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupPostsRecyclerView()

        initObservers()

        if (viewModel.postsResponse.nonNullValue.initial) {
            viewModel.loadPosts()
        }
    }

    private fun setupPostsRecyclerView() {
        postsAdapter = PostsAdapter { post ->
            navigate(PostsFragmentDirections.actionOpenPostDetailsFragment(post, post.title))
        }
        binding.postsRecyclerView.adapter = postsAdapter
    }

    private fun initObservers() {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            postsAdapter.submitList(posts)
        }
        viewModel.postsResponse.observe(viewLifecycleOwner) { result ->
            if (result is Result.Error) {
                showSnackBarWithAction(result.message, Snackbar.LENGTH_INDEFINITE) {
                    viewModel.loadPosts()
                }
            }
        }
    }
}