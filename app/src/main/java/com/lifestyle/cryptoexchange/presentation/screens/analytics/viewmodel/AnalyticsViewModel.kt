package com.lifestyle.cryptoexchange.presentation.screens.analytics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifestyle.cryptoexchange.data.model.Asset
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.domain.usecase.GetPortfolioAssetUseCase
import com.lifestyle.cryptoexchange.domain.usecase.GetRecentTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val getRecentTransactionsUseCase: GetRecentTransactionsUseCase,
    private val portfolioAssetUseCase: GetPortfolioAssetUseCase
) :
    ViewModel() {

    private val _recentTransactions = MutableStateFlow<List<Transactions>>(emptyList())
    val recentTransactions = _recentTransactions.asStateFlow()

    private val _portfolioAsset = MutableStateFlow<List<Asset>>(emptyList())
    val portfolioAsset = _portfolioAsset.asStateFlow()

    init {
        getRecentTransactions()
        getPortfolioAssetData()
    }

    private fun getRecentTransactions() {
        viewModelScope.launch {
            val recentTransactions = getRecentTransactionsUseCase.invoke()
            _recentTransactions.value = recentTransactions
        }
    }

    private fun getPortfolioAssetData() {
        viewModelScope.launch {
            val portfolioAsset = portfolioAssetUseCase.invoke()
            _portfolioAsset.value = portfolioAsset
        }
    }
}