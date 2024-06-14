package com.timife.vpdmoneyassessment.data.mocks


data class Account(
    val accountFirstName: String,
    val accountLastName: String,
    val accountNumber: String,
    val accountBalance: Int
)


val mockAccounts = mutableListOf(
    Account("John", "Ademola", "******9861", 45000),
    Account("Timothy", "Aeolus", "******9861", 500000),
    Account("Oluwaponmile", " Jane", "******9861", 50000),
    Account("Usman", "Macadamising", "******9861", 10000),
    Account("John", "Duke", "******9861", 87000),
    Account("Smart", "Wonder", "******9861", 65000),
    Account("Fata", "Ayodhya", "******9861", 1500000),
    Account("Theo", "Gabriel", "******9861", 200000),
    Account("Abel", "Cole", "******9861", 740000),
    Account("Jasmine", "Ruth", "******9861", 78000),
    Account("Caleb", "Paul", "******9861", 66000)
)
