package com.timife.vpdmoneyassessment.data.di

import android.app.Application
import androidx.room.Room
import com.timife.vpdmoneyassessment.data.database.AccountsDB
import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAccountsDatabase(app: Application): AccountsDB {
        return Room.databaseBuilder(
            app,
            AccountsDB::class.java,
            "accounts.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountsDao(
        accountsDB: AccountsDB
    ): AccountsDao {
        return accountsDB.dao
    }

    @Provides
    @Singleton
    fun provideTransactionDao(
        accountsDB: AccountsDB
    ): TransactionDao {
        return accountsDB.transactionDao
    }

}