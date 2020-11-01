package com.lupesoft.pruebadeingreso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.pruebadeingreso.R
import com.lupesoft.pruebadeingreso.data.user.UserVo
import com.lupesoft.pruebadeingreso.databinding.UserListItemBinding

class UserAdapter : ListAdapter<UserVo, RecyclerView.ViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as UserViewHolder).bind(user, position)
    }

    class UserViewHolder(
        private val binding: UserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.setClickListener {
                binding.user?.let { user ->
                    navigateUser(user, it)
                }
            }
        }

        private fun navigateUser(elem: UserVo, view: View) {
            view.findNavController().navigate(
                R.id.action_userListFragment_to_userPostsFragment, bundleOf(
                    "user" to elem
                )
            )
        }

        fun bind(item: UserVo, position: Int) {
            binding.apply {
                user = item
                isViewPost = false
                cvUser.tag = position
                executePendingBindings()
            }
        }
    }
}

private class UserDiffCallback : DiffUtil.ItemCallback<UserVo>() {

    override fun areItemsTheSame(oldItem: UserVo, newItem: UserVo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserVo, newItem: UserVo): Boolean {
        return oldItem == newItem
    }
}