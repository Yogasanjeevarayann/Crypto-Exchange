package com.lifestyle.cryptoexchange.domain.usecase

import com.lifestyle.cryptoexchange.domain.repository.TransactionRepository
import javax.inject.Inject

class GetRecentTransactionsUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {
    operator fun invoke() = transactionRepository.getRecentTransactions()
}