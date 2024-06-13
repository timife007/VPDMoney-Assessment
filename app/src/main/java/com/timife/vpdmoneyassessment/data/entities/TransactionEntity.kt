package com.timife.vpdmoneyassessment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transaction_entity")
data class TransactionEntity (
    @PrimaryKey
    val id:Int,
    val sender: String,
    val receiver: String,
    val amount: String,
    val status: String
)