package com.lupesoft.pruebadeingreso.data.post


data class Post(
    val postId: Int,
    val userId: Int,
    val title: String,
    val body: String
)