package com.userposts.testapplication.feature.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.userposts.testapplication.domain.interactor.PostsInteractor
import com.userposts.testapplication.domain.model.UserPostResult
import com.userposts.testapplication.feature.InternetObserver
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel(
    private val postsInteractor: PostsInteractor,
    private val internetObserver: InternetObserver
) : ViewModel() {

    val showGeneralErrorEvent = SingleLiveEvent<Unit>()

    private val onInternetStateListener = object : InternetObserver.OnInternetStateListener {
        override fun onInternetStateChanged(isOnline: Boolean) {
            if (!isOnline) showGeneralErrorEvent.call()
        }
    }

    val postsLiveEvent = MutableLiveData<List<UserPostResult>>()

    val progressLiveEvent = MutableLiveData<Boolean>()

    init {
        internetObserver.onCreate()
        internetObserver.setListener(onInternetStateListener)
        getPosts()
    }

    fun refreshPosts() {
        viewModelScope.launch {
            kotlin.runCatching { postsInteractor.refreshPosts() }
                .onFailure {
                    onErrorCatch()
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        internetObserver.onDestroy()
    }

    private fun getPosts() {
        viewModelScope.launch {
            progressLiveEvent.postValue(true)
            postsInteractor
                .getPosts()
                .catch { ex ->
                    onErrorCatch()
                }
                .collect { result ->
                    result
                        .onSuccess { posts ->
                            postsLiveEvent.postValue(posts)
                            progressLiveEvent.postValue(false)
                        }
                        .onFailure {
                            onErrorCatch()
                            progressLiveEvent.postValue(false)
                        }
                }
        }
    }

    private fun onErrorCatch() {
        showGeneralErrorEvent.call()
    }
}