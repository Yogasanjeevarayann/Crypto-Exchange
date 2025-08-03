package com.lifestyle.cryptoexchange.data.repository

import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.data.model.Asset
import com.lifestyle.cryptoexchange.data.model.TransactionPortfolioData
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.domain.repository.TransactionRepository
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.ImageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TransactionRepositoryImpl() : TransactionRepository {
    override fun getTransactions(): List<Transactions> {
        return listOf(
            Transactions(
                R.drawable.arrow_down,
                "Receive",
                "BTC",
                "+0.002126",
                "20 March",
                ImageType.HALF_ROUND
            ),
            Transactions(
                R.drawable.arrow_up,
                "Sent",
                "ETH",
                "+0.002126",
                "19 March", ImageType.HALF_ROUND

            ),
            Transactions(
                R.drawable.arrow_up,
                "Sent",
                "LTC",
                "+0.002126",
                "18 March",
                ImageType.HALF_ROUND
            ),
            Transactions(
                R.drawable.arrow_down,
                "Receive",
                "BTC",
                "+0.002126",
                "17 March",
                ImageType.HALF_ROUND
            )
        )

    }

    override fun getTransactionsPortfolioData(): TransactionPortfolioData {
        return TransactionPortfolioData(
            "1,57,342.05",
            "₹ 1,342",
            "INR",
            "+4.6%"
        )
    }

    override fun getRecentTransactions(): List<Transactions> {
        return listOf(
            Transactions(
                R.drawable.bitcoin_image,
                "Receive",
                "BTC",
                "+0.002126",
                "20 March",
                ImageType.FULL
            ),
            Transactions(
                R.drawable.self,
                "Receive",
                "BTC",
                "+0.002126",
                "20 March"
            ),
            Transactions(
                R.drawable.self,
                "Receive",
                "BTC",
                "+0.002126",
                "20 March"
            ),
            Transactions(
                R.drawable.self,
                "Receive",
                "BTC",
                "+0.002126",
                "20 March"
            )
        )
    }

    override fun getPortfolioAssetData(): List<Asset> {
        return listOf(
            Asset(R.drawable.bitcoin_image, "Bitcoin (BTC)", "₹ 75,62,502.14", "+3.2%"),
            Asset(R.drawable.ethereum_image, "Ether (ETH)", "₹ 1,79,102.50", "+3.4%")
        )
    }
}
