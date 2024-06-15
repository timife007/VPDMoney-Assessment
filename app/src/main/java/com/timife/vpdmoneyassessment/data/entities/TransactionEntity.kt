package com.timife.vpdmoneyassessment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transaction_entity")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val sender: String,
    val receiver: String,
    val amount: String,
    val date:String,
    val time:String
)