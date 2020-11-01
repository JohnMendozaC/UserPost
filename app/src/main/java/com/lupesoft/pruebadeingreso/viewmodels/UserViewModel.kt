package com.lupesoft.pruebadeingreso.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lupesoft.pruebadeingreso.data.Resource
import com.lupesoft.pruebadeingreso.data.user.UserRepository
import com.lupesoft.pruebadeingreso.data.user.UserVo

class UserViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val users: LiveData<Resource<List<UserVo>>> = repository.getFromDatabase().asLiveData()
}