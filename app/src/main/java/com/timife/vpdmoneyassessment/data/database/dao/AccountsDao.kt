package com.timife.vpdmoneyassessment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsertAccounts(
        accounts: List<AccountEntity>,
    )

    @Query("UPDATE account_entity SET accountBalance = accountBalance - :moneySent WHERE id = :id")
    suspend fun sendMoney(id: Int, moneySent: Int)

    @Query("UPDATE account_entity SET accountBalance = accountBalance + :moneyReceived WHERE id = :id")
    suspend fun receiveMoney(id: Int, moneyReceived: Int)

    @Query("SELECT * FROM account_entity")
    fun getALlAccounts(): Flow<List<AccountEntity>>

}