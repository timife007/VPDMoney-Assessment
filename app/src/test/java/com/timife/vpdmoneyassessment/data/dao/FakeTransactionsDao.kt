package com.timife.vpdmoneyassessment.data.dao

import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTransactionsDao : TransactionDao {

    private val fakeTransactionDb = emptyList<TransactionEntity>()
    override suspend fun insertTransaction(transaction: TransactionEntity) {
        fakeTransactionDb + transaction
    }

    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return flow {
            emit(fakeTransactionDb)
        }
    }
}