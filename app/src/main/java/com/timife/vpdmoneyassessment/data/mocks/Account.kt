package com.timife.vpdmoneyassessment.data.mocks


data class Account(
    val accountFirstName: String,
    val accountLastName: String,
    val accountNumber: String,
    val accountBalance: Int,
    val bank:String
)


val mockAccounts = mutableListOf(
    Account("John", "Ademola", "0123145665", 45000, "WemaBank"),
    Account("Timothy", "Aeolus", "1011121314", 500000, "GTBank"),
    Account("Oluwaponmile", "Jane", "1516171819", 50000, "GTBank"),
    Account("Usman", "Macadamising", "6009303039", 10000, "Zenith"),
    Account("John", "Duke", "1001121443", 87000, "Fidelity"),
    Account("Smart", "Wonder", "0021345783", 65000,"FirstBank"),
    Account("Fata", "Ayodhya", "8121223440", 1500000, "OPay"),
    Account("Theo", "Gabriel", "1232219393", 200000, "CashApp"),
    Account("Abel", "Cole", "0973863736", 740000, "UBA"),
    Account("Jasmine", "Ruth", "7778383839", 78000, "Oceanic"),
    Account("Caleb", "Paul", "01234223404", 66000, "Zenith")
)
