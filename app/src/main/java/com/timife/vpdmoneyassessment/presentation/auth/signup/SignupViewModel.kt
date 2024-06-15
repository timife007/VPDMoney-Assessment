package com.timife.vpdmoneyassessment.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.vpdmoneyassessment.data.mockdata.mockAccounts
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
): ViewModel() {


    fun saveAccounts(){
        viewModelScope.launch {
            accountsRepository.saveAccounts(mockAccounts)
        }
    }
}