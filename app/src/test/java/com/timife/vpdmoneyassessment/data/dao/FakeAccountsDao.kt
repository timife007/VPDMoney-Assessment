package com.timife.vpdmoneyassessment.data.dao

import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAccountsDao() : AccountsDao {

    private var fakeDatabase = emptyList<AccountEntity>()

    override suspend fun upsertAccounts(accounts: List<AccountEntity>) {
        fakeDatabase = fakeDatabase + accounts
    }

    override suspend fun getAccount(acctNo: String): AccountEntity {
        val data = fakeDatabase.first {
            it.accountNumber == acctNo
        }
        return data
    }

    override suspend fun deductMoneyFrom(acctNo: String, moneySent: Double) {
        fakeDatabase.forEach {
            if(it.accountNumber == acctNo){
                val newEntity = it.copy(accountBalance = it.accountBalance - moneySent.toInt())
                fakeDatabase + newEntity
            }
        }
    }

    override suspend fun sendMoneyTo(acctNo: String, moneyReceived: Double) {
        fakeDatabase.forEach {
            if(it.accountNumber == acctNo){
                val newEntity = it.copy(accountBalance = it.accountBalance + moneyReceived.toInt())
                fakeDatabase + newEntity
            }
        }
    }

    override fun getALlAccounts(): Flow<List<AccountEntity>> {
        return flow {
            emit(fakeDatabase)
        }
    }

}