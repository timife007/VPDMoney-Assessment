package com.timife.vpdmoneyassessment.presentation.summary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.vpdmoneyassessment.data.mockdata.Account
import com.timife.vpdmoneyassessment.data.mockdata.Transaction
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TransactionSummaryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AccountsRepository
) : ViewModel() {
    private val _senderDetails = MutableLiveData<Account>()
    val senderDetails: LiveData<Account> = _senderDetails

    private val _receiverDetails = MutableLiveData<Account>()
    val receiverDetails: LiveData<Account> = _receiverDetails

    private val _remark = MutableLiveData<String>()
    val remark: LiveData<String> = _remark

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _transferState = MutableLiveData<TransferState>()
    val transferState: LiveData<TransferState> = _transferState

    init {
        savedStateHandle.get<String>("senderAcct")?.let {
            getSenderDetails(it)
        }

        savedStateHandle.get<String>("receiverAcct")?.let {
            getReceiverDetails(it)
        }

        savedStateHandle.get<String>("remark")?.let {
            _remark.value = it
        }

        savedStateHandle.get<Int>("amount")?.let {
            _amount.value = it.toString()
        }

    }


    private fun getSenderDetails(senderAcct: String) {
        viewModelScope.launch {
            val details = repository.getAccountDetail(senderAcct)
            _senderDetails.postValue(details)
        }
    }

    private fun getReceiverDetails(senderAcct: String) {
        viewModelScope.launch {
            val details = repository.getAccountDetail(senderAcct)
            _receiverDetails.postValue(details)
        }
    }

    fun makeTransfer() {
        viewModelScope.launch {
            _transferState.value = TransferState(loading = true)
            try {
                val senderName = senderDetails.value?.accountNumber?.let {
                    repository.getAccountDetail(
                        it
                    ).accountFirstName
                }
                val receiverName = _receiverDetails.value?.accountNumber?.let {
                    repository.getAccountDetail(
                        it
                    ).accountFirstName
                }
                val amountSent = _amount.value?.toDouble()
                val senderAcct = _senderDetails.value?.accountNumber
                val receiverAcct = _receiverDetails.value?.accountNumber
                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val formatter12h = DateTimeFormatter.ofPattern("h:mm:ss a")

                val transaction = Transaction(
                    senderName,
                    receiverName,
                    LocalDateTime.now().format(dateFormatter),
                    LocalDateTime.now().format(formatter12h),
                    amountSent.toString()
                )
                if (amountSent != null && senderAcct != null && receiverAcct != null) {
                    repository.makeTransfer(
                        senderAcct.toString(),
                        receiverAcct.toString(),
                        amountSent.toDouble(),
                        transaction
                    )
                    _transferState.value = TransferState(success = true)
                }
            } catch (e: Exception) {
                _transferState.value = TransferState(error = e.localizedMessage)
            }

        }
    }
}

data class TransferState(
    val success: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)