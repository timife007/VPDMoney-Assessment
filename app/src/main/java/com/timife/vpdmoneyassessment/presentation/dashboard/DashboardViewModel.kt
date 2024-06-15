package com.timife.vpdmoneyassessment.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.vpdmoneyassessment.data.mockdata.Transaction
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import com.timife.vpdmoneyassessment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _transactions = MutableLiveData<TransactionListState>()
    val transactions: LiveData<TransactionListState> = _transactions

    private val _sum = MutableLiveData<String>()
    val sum: LiveData<String> = _sum

    init {
        getAllTransactions()
        getSum()
    }

    private fun getSum() {
        viewModelScope.launch {
            accountsRepository.getAllAccounts().collect {
                when (it) {
                    is Resource.Error -> {
                        if (it.data != null) {
                            _sum.value = "**** **** **** ****"
                        }
                    }

                    is Resource.Loading -> {
                        _sum.value = "**** **** **** ****"
                    }

                    is Resource.Success -> {
                        var sum = 0
                        it.data?.forEach { account ->
                            sum += account.accountBalance
                        }
                        _sum.value = sum.toString()
                    }
                }
            }
        }
    }

    private fun getAllTransactions() {
        viewModelScope.launch {
            accountsRepository.getAllTransactions().collect {
                when (it) {
                    is Resource.Error -> {
                        if (it.data != null) {
                            _transactions.value = TransactionListState(error = it.message)
                        }
                    }

                    is Resource.Loading -> {
                        _transactions.value = TransactionListState(loading = true)
                    }

                    is Resource.Success -> {
                        _transactions.value = TransactionListState(data = it.data)
                    }
                }
            }
        }
    }
}

data class TransactionListState(
    val loading: Boolean = false,
    val data: List<Transaction>? = emptyList(),
    val error: String? = null,
)