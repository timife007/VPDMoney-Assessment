package com.timife.vpdmoneyassessment.presentation.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import com.timife.vpdmoneyassessment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _accounts = MutableLiveData<AccountListState>()
    val accounts: LiveData<AccountListState> = _accounts

    init {
        getAllAccounts()
    }

    private fun getAllAccounts() {
        viewModelScope.launch {
            accountsRepository.getAllAccounts().collect {
                when (it) {
                    is Resource.Error -> {
                        if (it.data != null) {
                            _accounts.value = AccountListState(error = it.message)
                        }
                    }

                    is Resource.Loading -> {
                        _accounts.value = AccountListState(loading = true)
                    }

                    is Resource.Success -> {
                        _accounts.value = AccountListState(data = it.data)
                    }
                }
            }
        }
    }
}

data class AccountListState(
    val loading: Boolean = false,
    val data: List<Account>? = emptyList(),
    val error: String? = null
)