package com.timife.vpdmoneyassessment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertAccounts(
        accounts: List<AccountEntity>,
    )

    @Query("SELECT * FROM account_entity WHERE accountNumber = :acctNo LIMIT 1")
    suspend fun getAccount(acctNo: String): AccountEntity

    @Query("UPDATE account_entity SET accountBalance = accountBalance - :moneySent WHERE accountNumber = :acctNo")
    suspend fun deductMoneyFrom(acctNo: String, moneySent: Double)

    @Query("UPDATE account_entity SET accountBalance = accountBalance + :moneyReceived WHERE accountNumber = :acctNo")
    suspend fun sendMoneyTo(acctNo: String, moneyReceived: Double)

    @Query("SELECT * FROM account_entity")
    fun getALlAccounts(): Flow<List<AccountEntity>>

    @Transaction
    suspend fun performMoneyTransfer(
        senderAcctNo: String,
        receiverAcctNo: String,
        amount: Double,
    ) {
        sendMoneyTo(receiverAcctNo, amount)
        deductMoneyFrom(senderAcctNo, amount)
    }

}