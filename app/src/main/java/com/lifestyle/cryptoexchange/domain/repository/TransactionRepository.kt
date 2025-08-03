package com.lifestyle.cryptoexchange.domain.repository

import com.lifestyle.cryptoexchange.data.model.Asset
import com.lifestyle.cryptoexchange.data.model.TransactionPortfolioData
import com.lifestyle.cryptoexchange.data.model.Transactions
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): List<Transactions>
    fun getTransactionsPortfolioData(): TransactionPortfolioData
    fun getRecentTransactions(): List<Transactions>
    fun getPortfolioAssetData():List<Asset>
}