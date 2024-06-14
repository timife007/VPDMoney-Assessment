package com.timife.vpdmoneyassessment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_entity")
data class AccountEntity(
    @PrimaryKey
    val id: Int,
    val accountName:String,
    val accountNumber:String,
    val accountBalance:Int
)