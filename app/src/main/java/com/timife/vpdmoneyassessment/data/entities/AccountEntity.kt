package com.timife.vpdmoneyassessment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_entity")
data class AccountEntity(
    @PrimaryKey
    val accountNumber: String,
    val accountFirstName: String,
    val accountLastName: String,
    val bank: String,
    var accountBalance: Int = 0
)