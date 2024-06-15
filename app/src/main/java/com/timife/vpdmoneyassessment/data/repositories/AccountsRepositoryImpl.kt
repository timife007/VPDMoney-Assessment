package com.timife.vpdmoneyassessment.data.repositories

import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import com.timife.vpdmoneyassessment.data.mappers.toAccount
import com.timife.vpdmoneyassessment.data.mappers.toAccountEntity
import com.timife.vpdmoneyassessment.data.mappers.toTransaction
import com.timife.vpdmoneyassessment.data.mappers.toTransactionEntity
import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import com.timife.vpdmoneyassessment.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao,
    private val transactionDao: TransactionDao,
) : AccountsRepository {
    override suspend fun makeTransfer(
        senderAcct: String,
        receiverAcct: String,
        amount: Double,
        transaction: Transaction
    ): Resource<String> {
        try {
            val sender = accountsDao.getAccount(senderAcct)

            if (sender.accountBalance < amount) {
                return Resource.Error("Insufficient Funds to make transfer")
            }

            accountsDao.performMoneyTransfer(
                senderAcct,
                receiverAcct,
                amount
            )
            transactionDao.insertTransaction(transaction.toTransactionEntity())
            return Resource.Success("Funds Successfully Transferred")
        } catch (e: Exception) {
            return Resource.Error("Error Making Transfer: ${e.message}")
        }
    }

    override fun getAllAccounts(): Flow<Resource<List<Account>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                accountsDao.getAllAccounts().collect { accounts ->
                    emit(Resource.Success(accounts.map {
                        it.toAccount()
                    }))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
            emit(Resource.Loading(false))

        }
    }

    override fun getAllTransactions(): Flow<Resource<List<Transaction>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                transactionDao.getAllTransactions().collect { transactions ->
                    emit(Resource.Success(transactions.map {
                        it.toTransaction()
                    }))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Error fetching Transaction"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun saveAccounts(accounts: List<Account>) {
        val accountEntities = accounts.map { it.toAccountEntity() }
        accountsDao.upsertAccounts(accountEntities)
    }

    override suspend fun getAccountDetail(accountNumber: String) : Account{
        return accountsDao.getAccount(accountNumber).toAccount()
    }
}