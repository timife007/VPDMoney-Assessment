package com.timife.vpdmoneyassessment.data.repositories

import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao,
    private val transactionDao: TransactionDao
): AccountsRepository {
    override fun makeTransfer(sender: Account, receiver: Account): Result<String> {
        TODO("Not yet implemented")
    }

    override fun getAllAccounts(): Flow<Account> {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): Flow<Transaction> {
        TODO("Not yet implemented")
    }


}