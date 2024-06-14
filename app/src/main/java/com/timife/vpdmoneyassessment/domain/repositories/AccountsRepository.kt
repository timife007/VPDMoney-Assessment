package com.timife.vpdmoneyassessment.domain.repositories

import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    suspend fun makeTransfer(
        senderAcct: String,
        receiverAcct: String,
        amount: Double,
        transaction: Transaction
    ): Resource<String>

    fun getAllAccounts(): Flow<Resource<List<Account>>>

    fun getAllTransactions(): Flow<Resource<List<Transaction>>>

    suspend fun saveAccounts(accounts:List<Account>)

    suspend fun getAccountDetail(accountNumber:String): Account
}