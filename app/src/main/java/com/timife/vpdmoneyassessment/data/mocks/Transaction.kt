package com.timife.vpdmoneyassessment.data.mocks

data class Transaction(
    val sender: String,
    val receiver:String,
    val transactionStatus: TransactionStatus,
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

val mockTransaction = mutableListOf(
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000"),
    Transaction("John", "Pius",
        TransactionStatus.SUCCESSFUL,  "2/10/22", "7:35pm","65000")
//    Transaction(
//        TransactionStatus.FAILED,
//        TransactionType.Transfer,"Jane", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.SUCCESSFUL,
//        TransactionType.Recharge,"Usman", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.FAILED,
//        TransactionType.Electricity,"Duke", "2/10/22", "7:35pm","65000"),
//    Transaction(TransactionStatus.PENDING, TransactionType.Transfer,"Wonder", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.SUCCESSFUL,
//        TransactionType.Electricity,"Ayodhya", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.PENDING,
//        TransactionType.Transfer,"Gabriel", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.SUCCESSFUL,
//        TransactionType.Transfer,"Cole", "2/10/22", "7:35pm","65000"),
//    Transaction(
//        TransactionStatus.SUCCESSFUL,
//        TransactionType.Recharge,"Jasmine", "2/10/22", "7:35pm","65000"),
//    Transaction(TransactionStatus.FAILED, TransactionType.Transfer,"Paul", "2/10/22", "7:35pm","65000")
)
