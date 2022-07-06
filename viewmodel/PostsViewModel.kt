package com.sample.app.viewmodel

import androidx.lifecycle.*
import com.sample.app.data.Result
import com.sample.app.data.models.Post
import com.sample.app.data.repository.PostRepository
import com.sample.app.util.isActive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private var jobFetchPosts: Job? = null
    private val _postsResponse = MutableLiveData<Result<Unit>>(Result.Initial)
    val postsResponse: LiveData<Result<Unit>> = _postsResponse

    val posts: LiveData<List<Post>> = postRepository.observePosts().asLiveData()

    fun loadPosts() {
        if (jobFetchPosts.isActive) return
        jobFetchPosts = viewModelScope.launch {
            postRepository.fetchPosts().collect {
                _postsResponse.value = it
            }
        }
    }
}