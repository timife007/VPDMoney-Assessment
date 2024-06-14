package com.timife.vpdmoneyassessment.data.mappers

import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity
import com.timife.vpdmoneyassessment.data.mocks.Account
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.data.mocks.TransactionStatus

fun Account.toAccountEntity(): AccountEntity {
    return AccountEntity(
        accountNumber = accountNumber,
        accountFirstName = accountFirstName,
        accountLastName = accountLastName,
        accountBalance = accountBalance
    )
}

fun AccountEntity.toAccount(): Account {
    return Account(
        accountNumber = accountNumber,
        accountFirstName = accountFirstName,
        accountLastName = accountLastName,
        accountBalance = accountBalance
    )
}


fun Transaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        sender = sender,
        receiver = receiver,
        amount = amount,
        status = transactionStatus.name,
        date = date,
        time = time
    )
}

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        sender = sender,
        receiver = receiver,
        amount = amount,
        transactionStatus = TransactionStatus.PENDING,
        date = date,
        time = time
    )
}