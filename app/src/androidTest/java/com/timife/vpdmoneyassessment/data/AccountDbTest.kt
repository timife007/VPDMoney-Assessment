package com.timife.vpdmoneyassessment.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.timife.vpdmoneyassessment.data.database.AccountsDB
import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AccountDbTest {

    @get:Rule
    var instantTaskExecutionRule = InstantTaskExecutorRule()

    private lateinit var database: AccountsDB
    private lateinit var dao: AccountsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AccountsDB::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    suspend fun upsertAccounts() {
        dao.upsertAccounts(accountEntities)
        assertThat(dao.getALlAccounts()).isNotNull()
    }


    @Test
    suspend fun getAllAccounts() {
        dao.upsertAccounts(accountEntities)
        assertThat(dao.getALlAccounts().first()).isNotNull()
    }

    companion object {
        val accountEntities = listOf(
            AccountEntity(
                "01478292",
                "Timothy",
                "Ifeoluwa",
                "UBA",
                24000,
            ),
            AccountEntity(
                "8133848484",
                "Ifeoluwa",
                "Ademola",
                "OPay",
                1000
            )
        )
    }
}