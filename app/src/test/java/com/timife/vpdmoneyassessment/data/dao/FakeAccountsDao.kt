package com.timife.vpdmoneyassessment.data.dao

import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAccountsDao() : AccountsDao {

    private var fakeDatabase = mutableListOf<AccountEntity>()

    override suspend fun upsertAccounts(accounts: List<AccountEntity>) {
//        fakeDatabase = mutableListOf()
        fakeDatabase.addAll(accounts)
    }

    override suspend fun getAccount(acctNo: String): AccountEntity {
        val data = fakeDatabase.first {
            it.accountNumber == acctNo
        }
        return data
    }

    override suspend fun deductMoneyFrom(acctNo: String, moneySent: Double) {
        for (i in fakeDatabase.indices) {
            if (fakeDatabase[i].accountNumber == acctNo) {
                var temp = fakeDatabase[i]
                temp = temp.copy(accountBalance = temp.accountBalance - moneySent.toInt())
                fakeDatabase.removeAt(i)
                fakeDatabase.add(temp)
                break
            }
        }
    }

    override suspend fun sendMoneyTo(acctNo: String, moneyReceived: Double) {
        for(j in fakeDatabase.indices){
            if(fakeDatabase[j].accountNumber == acctNo){
                var temp = fakeDatabase[j]
                temp = temp.copy(accountBalance = temp.accountBalance + moneyReceived.toInt())
                fakeDatabase.removeAt(j)
                fakeDatabase.add(temp)
                break
            }
        }
    }


    override fun getAllAccounts(): Flow<List<AccountEntity>> {
        return flow {
            emit(fakeDatabase)
        }
    }

    override suspend fun performMoneyTransfer(
        senderAcctNo: String,
        receiverAcctNo: String,
        amount: Double
    ) {
        sendMoneyTo(receiverAcctNo, amount)
        deductMoneyFrom(senderAcctNo, amount)
    }


}