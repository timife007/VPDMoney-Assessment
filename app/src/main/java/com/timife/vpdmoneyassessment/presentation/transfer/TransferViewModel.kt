package com.timife.vpdmoneyassessment.presentation.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.vpdmoneyassessment.data.mockdata.Account
import com.timife.vpdmoneyassessment.data.mockdata.mockAccounts
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _accountDetail = MutableLiveData<Account>()
    val accountDetail: LiveData<Account> = _accountDetail

     fun isAcctNoValid(acctNo:String):Boolean{
        mockAccounts.forEach {
            if (it.accountNumber == acctNo){
                return true
            }
        }
        return false
    }


    fun fetchAccountDetails(accountNumber:String){
        viewModelScope.launch {
            val accountDetail = accountsRepository.getAccountDetail(accountNumber)
            _accountDetail.postValue(accountDetail)
        }
    }
}