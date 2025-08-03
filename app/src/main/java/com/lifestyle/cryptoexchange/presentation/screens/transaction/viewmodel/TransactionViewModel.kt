package com.lifestyle.cryptoexchange.presentation.screens.transaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifestyle.cryptoexchange.data.model.TransactionPortfolioData
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.domain.usecase.GetPortfolioValueUseCase
import com.lifestyle.cryptoexchange.domain.usecase.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getPortfolioValueUseCase: GetPortfolioValueUseCase
) : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transactions>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private val _transactionsPortfolio = MutableStateFlow(TransactionPortfolioData())
    val transactionsPortfolio = _transactionsPortfolio.asStateFlow()

    init {
        getTransactions()
        getPortfolioData()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            val transactions = getTransactionsUseCase.invoke()
            _transactions.value = transactions
        }
    }

    private fun getPortfolioData() {
        viewModelScope.launch {
            val portfolioData = getPortfolioValueUseCase.invoke()
            _transactionsPortfolio.value = portfolioData
        }
    }
}