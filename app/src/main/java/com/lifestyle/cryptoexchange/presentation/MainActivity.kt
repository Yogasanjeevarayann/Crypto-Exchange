package com.lifestyle.cryptoexchange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.presentation.components.BottomNavBar
import com.lifestyle.cryptoexchange.presentation.navigation.NavItem
import com.lifestyle.cryptoexchange.presentation.navigation.Screens
import com.lifestyle.cryptoexchange.presentation.screens.analytics.AnalyticsScreen
import com.lifestyle.cryptoexchange.presentation.screens.exchange.ExchangeScreen
import com.lifestyle.cryptoexchange.presentation.screens.RecordScreen
import com.lifestyle.cryptoexchange.presentation.screens.WalletScreen
import com.lifestyle.cryptoexchange.presentation.screens.analytics.viewmodel.AnalyticsViewModel
import com.lifestyle.cryptoexchange.presentation.screens.transaction.TransactionSummary
import com.lifestyle.cryptoexchange.presentation.screens.transaction.viewmodel.TransactionViewModel
import com.lifestyle.cryptoexchange.ui.theme.Background
import com.lifestyle.cryptoexchange.ui.theme.CryptoExchangeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val navItems = listOf(
        NavItem("Analytics", Screens.Analytics, R.drawable.analytics),
        NavItem("Exchange", Screens.Transaction, R.drawable.exchange),
        NavItem("Record", Screens.Record, R.drawable.chart),
        NavItem("Wallet", Screens.Wallet, R.drawable.wallet),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoExchangeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Background,
                    bottomBar = {
                        if (currentDestination != Screens.Exchange.route) {
                            BottomNavBar(navItems, currentDestination, navController)
                        }

                    },
                    content = { innerPadding ->
                        MainScreen(navController, innerPadding)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = Screens.Analytics.route,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(Screens.Analytics.route) {
            val analyticsViewModel: AnalyticsViewModel = hiltViewModel()
            AnalyticsScreen(analyticsViewModel)
        }
        composable(Screens.Exchange.route) {
            ExchangeScreen {
                navController.navigateUp()
            }
        }
        composable(Screens.Transaction.route) {
            val transactionViewModel: TransactionViewModel = hiltViewModel()
            TransactionSummary(transactionViewModel) {
                navController.navigate(Screens.Exchange.route)
            }
        }
        composable(Screens.Record.route) {
            RecordScreen()
        }
        composable(Screens.Wallet.route) {
            WalletScreen()
        }
    }
}




