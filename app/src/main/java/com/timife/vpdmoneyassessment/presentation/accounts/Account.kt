package com.timife.vpdmoneyassessment.presentation.accounts

data class Account(
    val accountName:String,
    val accountNumber:String,
    val accountBalance:String
)

val mockData = listOf(
    Account("Ademola John", "******9861", "N500000"),
    Account("Timothy Aeolus", "******9861", "N500000"),
    Account("Oluwaponmile Jane", "******9861", "N500000"),
    Account("Macadamising Usman", "******9861", "N500000"),
    Account("John Duke", "******9861", "N500000"),
    Account("Smart Wonder", "******9861", "N750000"),
    Account("Fata Ayodhya", "******9861", "N500000"),
    Account("Theo Gabriel", "******9861", "N500000"),
    Account("Abel Cole", "******9861", "N500000"),
    Account("Jasmine Ruth", "******9861", "N500000"),
    Account("Caleb Paul", "******9861", "N500000")

)
