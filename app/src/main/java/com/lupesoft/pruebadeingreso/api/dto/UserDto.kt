package com.lupesoft.pruebadeingreso.api.dto

import com.google.gson.annotations.SerializedName
import com.lupesoft.pruebadeingreso.data.user.UserEntity
import com.lupesoft.pruebadeingreso.data.user.UserVo

data class UserDto(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username") val username: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("website") val website: String
)

data class Addressvo(
    @field:SerializedName("street") val street: String,
    @field:SerializedName("suite") val suite: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("zipcode") val zipcode: String,
    @field:SerializedName("geo") val geo: Geovo
)

data class Geovo(
    @field:SerializedName("lat") val lat: String,
    @field:SerializedName("lng") val lng: String
)

fun List<UserDto>.toDomainModel(): List<UserVo> {
    return map { UserVo(it.id, it.name, it.username, it.email, it.phone, it.website) }
}

fun List<UserDto>.toDatabaseModel(): List<UserEntity> {
    return map { UserEntity(it.id, it.name, it.username, it.email, it.phone, it.website) }
}