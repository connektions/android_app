package com.example.connektions.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connektions.common.Result
import com.example.connektions.data.model.User
import com.example.connektions.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel(
    private val _profileRepository: ProfileRepository
) : ViewModel() {
    private val _userProfile = MutableStateFlow<Result<User>>(Result.Loading)
    val userProfile: StateFlow<Result<User>> = _userProfile

    init {
        viewModelScope.launch {
            getUserProfile()
        }
    }

    private suspend fun getUserProfile() {
        viewModelScope.launch {
            _userProfile.value = _profileRepository.getUserProfile()
        }
    }
}
