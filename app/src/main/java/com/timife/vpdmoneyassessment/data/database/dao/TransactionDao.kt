package com.timife.vpdmoneyassessment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(
        transaction: TransactionEntity,
    )

    @Query("SELECT * FROM transaction_entity")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

}