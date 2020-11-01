package com.lupesoft.pruebadeingreso.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)

fun List<UserEntity>.toDomainModel(): List<UserVo> {
    return map { UserVo(it.id, it.name, it.username, it.email, it.phone, it.website) }
}