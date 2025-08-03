package com.lifestyle.cryptoexchange.data.model

data class TransactionPortfolioData(
    val totalAmount: String,
    val amount: String,
    val amountType: String,
    val increasePercent: String
){
    constructor(): this("","","","")
}
