package com.lupesoft.pruebadeingreso.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lupesoft.pruebadeingreso.data.Resource
import com.lupesoft.pruebadeingreso.data.post.Post
import com.lupesoft.pruebadeingreso.data.post.PostRepository

class UserPostViewModel @ViewModelInject constructor(
    private val repository: PostRepository
) : ViewModel() {
    fun posts(id: Int): LiveData<Resource<List<Post>>> = repository.getFromNetwork(id).asLiveData()
}