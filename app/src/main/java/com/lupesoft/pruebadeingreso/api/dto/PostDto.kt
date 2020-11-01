package com.lupesoft.pruebadeingreso.api.dto

import com.google.gson.annotations.SerializedName
import com.lupesoft.pruebadeingreso.data.post.Post

data class PostDto(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("userId") val userId: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("body") val body: String
)

fun List<PostDto>.toDomainModel(): List<Post> {
    return map { Post(it.id, it.userId, it.title, it.body) }
}

