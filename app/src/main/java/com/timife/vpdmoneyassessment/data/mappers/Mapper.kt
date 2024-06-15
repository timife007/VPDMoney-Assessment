package com.timife.vpdmoneyassessment.data.mappers

import com.timife.vpdmoneyassessment.data.entities.AccountEntity
import com.timife.vpdmoneyassessment.data.entities.TransactionEntity
import com.timife.vpdmoneyassessment.data.mockdata.Account
import com.timife.vpdmoneyassessment.data.mockdata.Transaction

fun Account.toAccountEntity(): AccountEntity {
    return AccountEntity(
        accountNumber = accountNumber,
        accountFirstName = accountFirstName,
        accountLastName = accountLastName,
        accountBalance = accountBalance,
        bank = bank
    )
}

fun AccountEntity.toAccount(): Account {
    return Account(
        accountNumber = accountNumber,
        accountFirstName = accountFirstName,
        accountLastName = accountLastName,
        accountBalance = accountBalance,
        bank = bank
    )
}


fun Transaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        sender = sender ?: "",
        receiver = receiver ?: "",
        amount = amount ?: "",
        date = date ?: "",
        time = time ?: ""
    )
}

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        sender = sender,
        receiver = receiver,
        amount = amount,
        date = date,
        time = time
    )
}