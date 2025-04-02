package com.example.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.UserListRepository
import com.example.assignment.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val userListRepository: UserListRepository): ViewModel() {

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
                    return UserListViewModel(userListRepository = UserListRepository(context)) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }


    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _validationResult = MutableStateFlow<Boolean?>(null)
    val validationResult = _validationResult.asStateFlow()


    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            try {
                _userList.value = userListRepository.userList
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun validateUser(id: String, phoneNumber: String) {
        viewModelScope.launch {
            _validationResult.value =
                userListRepository.validateUser(id = id, phoneNumber = phoneNumber)
        }
    }

    fun clearValidation() {
        viewModelScope.launch {
            _validationResult.value = null
        }
    }

}