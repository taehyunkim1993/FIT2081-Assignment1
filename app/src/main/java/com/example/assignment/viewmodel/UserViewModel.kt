package com.example.assignment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.UserRepository
import com.example.assignment.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                    return UserViewModel(userRepository = UserRepository(context)) as T
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

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()


    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            try {
                _userList.value = userRepository.userList
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
                userRepository.validateUser(id = id, phoneNumber = phoneNumber)
        }
    }

    fun clearValidation() {
        viewModelScope.launch {
            _validationResult.value = null
        }
    }

    fun updateCurrentUser(id: String) {
        viewModelScope.launch {
            val userFound = userRepository.getUserWithUserId(id = id)
            _currentUser.value = userFound
        }
    }
}