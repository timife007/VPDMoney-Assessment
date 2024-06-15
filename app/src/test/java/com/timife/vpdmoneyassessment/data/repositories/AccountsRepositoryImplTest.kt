package com.timife.vpdmoneyassessment.data.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.vpdmoneyassessment.data.dao.FakeAccountsDao
import com.timife.vpdmoneyassessment.data.dao.FakeTransactionsDao
import com.timife.vpdmoneyassessment.data.database.dao.AccountsDao
import com.timife.vpdmoneyassessment.data.database.dao.TransactionDao
import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity
import com.timife.vpdmoneyassessment.data.mappers.toAccount
import com.timife.vpdmoneyassessment.data.mappers.toTransaction
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.utils.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class AccountsRepositoryImplTest {

    private lateinit var repository: AccountsRepositoryImpl
    private lateinit var dao: AccountsDao
    private lateinit var transactionDao: TransactionDao

    @Before
    fun setUp() {
        dao = FakeAccountsDao()
        transactionDao = FakeTransactionsDao()
        repository = AccountsRepositoryImpl(accountsDao = dao, transactionDao = transactionDao)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun makeTransfer() = runTest {
        dao.upsertAccounts(accounts)
        assertThat(dao.getAllAccounts().first()).isNotEmpty()
        val sender = accounts[0]
        val receiver = accounts[1]
        val transferAmount = 5000.0
        val senderExpectedBalance = (sender.accountBalance - transferAmount).toInt()
        val receiverExpectedBalance = (receiver.accountBalance + transferAmount).toInt()
        repository.makeTransfer(
            sender.accountNumber,
            receiver.accountNumber,
            transferAmount,
            Transaction(
                sender.accountFirstName,
                receiver.accountFirstName,
                LocalDateTime.now().dayOfWeek.toString(),
                LocalDateTime.now().hour.toString(),
                "5000"
            )
        )

        assertThat(repository.getAccountDetail(sender.accountNumber).accountBalance).isEqualTo(
            senderExpectedBalance
        )
        assertThat(repository.getAccountDetail(receiver.accountNumber).accountBalance).isEqualTo(
            receiverExpectedBalance
        )
    }

    @Test
    fun getAllAccounts() = runTest {
        dao.upsertAccounts(accounts)
        repository.getAllAccounts().test {
            val loading = awaitItem()

            assertThat((loading as Resource.Loading).isLoading).isTrue()
            val localData = awaitItem()
            assertThat(localData is Resource.Success).isTrue()
            dao.getAllAccounts().collect {
                assertThat(localData.data).isEqualTo(it.map { acctEntity ->
                    acctEntity.toAccount()
                })
            }
            val lastLoading = awaitItem()
            assertThat((lastLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    @Test
    fun getAllTransactions() = runTest {
        transactions.forEach { transactionEntity ->
            transactionDao.insertTransaction(transactionEntity)
        }

        repository.getAllTransactions().test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()
            val localData = awaitItem()
            assertThat(localData is Resource.Success).isTrue()

            transactionDao.getAllTransactions().collect {
                assertThat(localData.data).isEqualTo(it.map { transactEntity ->
                    transactEntity.toTransaction()
                })
            }
            val lastLoading = awaitItem()
            assertThat((lastLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    @Test
    fun saveAccounts() = runTest {
        dao.upsertAccounts(accounts)
        assertThat(dao.getAllAccounts().first()).isNotEmpty()
        dao.getAllAccounts().collect { acctEntities ->
            assertThat(acctEntities).isEqualTo(accounts)
        }
    }

    @Test
    fun getAccountDetail() = runTest {
        dao.upsertAccounts(accounts)
        assertThat(dao.getAllAccounts().first()).isNotNull()
        val found = dao.getAccount("0123145665")
        assertThat(found).isEqualTo(accounts[0])
    }

    companion object {
        var accounts = listOf(
            AccountEntity("0123145665", "John", "Ademola", "WemaBank", 45000),
            AccountEntity("1011121314", "Timothy", "Aeolus", "GTBank", 500000), //20k
            AccountEntity("1516171819", "Oluwaponmile", "Jane", "GTBank", 50000),
            AccountEntity("6009303039", "Usman", "Macadamising", "Zenith", 10000),
        )

        var transactions = listOf(
            TransactionEntity(1, "Timothy", "Ifeoluwa", "30000", "21/10/2024", "10:00pm"),
            TransactionEntity(2, "Abraham", "Gabriel", "5000", "21/01/2024", "10:00pm"),
            TransactionEntity(3, "John", "Pius", "10000", "10/10/2023", "7:00pm"),
            TransactionEntity(4, "Timothy", "Ademola", "20000", "5/10/2023", "8:00pm")
        )
    }
}