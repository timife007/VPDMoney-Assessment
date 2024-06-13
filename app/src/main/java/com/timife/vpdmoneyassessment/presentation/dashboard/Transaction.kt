package com.timife.vpdmoneyassessment.presentation.dashboard

data class Transaction(
    val transactionStatus:TransactionStatus,
    val transactionType: TransactionType,
    val userName:String,
    val date: String,
    val time:String,
    val amount:String
)
enum class TransactionStatus{
    PENDING,
    SUCCESSFUL,
    FAILED,
}
enum class TransactionType{
    Electricity,
    Transfer,
    Recharge
}

val mockTransaction = listOf(
    Transaction(TransactionStatus.SUCCESSFUL,TransactionType.Transfer, "John", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.PENDING,TransactionType.Transfer,"Aeolus", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.FAILED,TransactionType.Transfer,"Jane", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.SUCCESSFUL,TransactionType.Recharge,"Usman", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.FAILED,TransactionType.Electricity,"Duke", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.PENDING, TransactionType.Transfer,"Wonder", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.SUCCESSFUL,TransactionType.Electricity,"Ayodhya", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.PENDING,TransactionType.Transfer,"Gabriel", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.SUCCESSFUL,TransactionType.Transfer,"Cole", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.SUCCESSFUL,TransactionType.Recharge,"Jasmine", "2/10/22", "7:35pm","65000"),
    Transaction(TransactionStatus.FAILED,TransactionType.Transfer,"Paul", "2/10/22", "7:35pm","65000")

)
