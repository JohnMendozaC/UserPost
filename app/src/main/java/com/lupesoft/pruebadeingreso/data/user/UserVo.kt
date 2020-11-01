package com.lupesoft.pruebadeingreso.data.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserVo(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
) : Parcelable