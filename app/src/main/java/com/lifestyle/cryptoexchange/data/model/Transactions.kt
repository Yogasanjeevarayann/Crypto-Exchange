package com.lifestyle.cryptoexchange.data.model

import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.ImageType

data class Transactions(
    val image: Int,
    val transactionStatus: String,
    val amountType: String,
    val amount: String,
    val date: String,
    val imageType: ImageType = ImageType.HALF_CENTER
) {
    constructor() : this(1, "", "", "", "")
}
