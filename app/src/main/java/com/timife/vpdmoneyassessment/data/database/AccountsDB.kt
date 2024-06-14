package com.timife.vpdmoneyassessment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity


@Database(
    entities = [AccountEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AccountsDB : RoomDatabase() {
    abstract val dao: AccountsDao
    abstract val transactionDao: TransactionDao
}