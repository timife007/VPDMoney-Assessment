package com.timife.vpdmoneyassessment.domain.repositories

import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    fun makeTransfer(sender: Account, receiver: Account): Result<String>

    fun getAllAccounts(): Flow<Account>

    fun getAllTransactions(): Flow<Transaction>
}