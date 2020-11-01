package com.lupesoft.pruebadeingreso.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lupesoft.pruebadeingreso.adapters.UserAdapter
import com.lupesoft.pruebadeingreso.data.Status
import com.lupesoft.pruebadeingreso.databinding.FragmentUserListBinding
import com.lupesoft.pruebadeingreso.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.userList.adapter = UserAdapter()
        subscribeUi()
        addSearchUser()
        return binding.root
    }

    private fun subscribeUi() {
        binding.loader.isLoading = false
        viewModel.users.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listUser = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        listUser = null
                    }
                }
            }
        }
    }

    private fun addSearchUser() {
        binding.apply {
            searchUser.setOnSearchClickListener {
                listUser?.also { listUserSearch = it }
            }
            searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    listUserSearch?.also { list ->
                        listUser = list.filter { it.name.contains(newText ?: "", true) }
                    }
                    return false
                }
            })
            searchUser.setOnCloseListener {
                listUserSearch?.also { listUser = it }
                listUserSearch = null
                false
            }
        }
    }
}