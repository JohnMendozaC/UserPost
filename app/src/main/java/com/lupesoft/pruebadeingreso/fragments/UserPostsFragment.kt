package com.lupesoft.pruebadeingreso.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lupesoft.pruebadeingreso.adapters.PostAdapter
import com.lupesoft.pruebadeingreso.data.Status
import com.lupesoft.pruebadeingreso.data.user.UserVo
import com.lupesoft.pruebadeingreso.databinding.FragmentUserPostsBinding
import com.lupesoft.pruebadeingreso.viewmodels.UserPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPostsFragment : Fragment() {

    private val viewModel: UserPostViewModel by viewModels()
    private lateinit var binding: FragmentUserPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPostsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val item = arguments?.get("user") as UserVo
        binding.userPostsList.adapter = PostAdapter()
        binding.userItem.apply {
            user = item
            isViewPost = true
        }
        subscribeUi(item.id)
        return binding.root
    }

    private fun subscribeUi(id: Int) {
        viewModel.posts(id).observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listPost = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        listPost = null
                    }
                }
            }
        }
    }
}