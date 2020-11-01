package com.lupesoft.pruebadeingreso.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.pruebadeingreso.data.post.Post
import com.lupesoft.pruebadeingreso.data.user.UserVo

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("setDataUser")
fun setDataUser(recycler: RecyclerView, data: List<UserVo>?) {
    data?.also {
        (recycler.adapter as UserAdapter).submitList(it)
    }
}

@BindingAdapter("setDataPost")
fun setDataPost(recycler: RecyclerView, data: List<Post>?) {
    data?.also {
        (recycler.adapter as PostAdapter).submitList(it)
    }
}