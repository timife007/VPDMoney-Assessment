package com.timife.vpdmoneyassessment.data.di

import com.timife.vpdmoneyassessment.data.repositories.AccountsRepositoryImpl
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAccountsRepository(
        accountsRepositoryImpl: AccountsRepositoryImpl
    ): AccountsRepository
}