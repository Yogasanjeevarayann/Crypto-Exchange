package com.lifestyle.cryptoexchange.presentation.navigation

sealed class Screens(val route: String) {
    data object Analytics : Screens("/analytics")
    data object Exchange : Screens("/exchange")
    data object Transaction : Screens("/transaction")
    data object Record : Screens("/record")
    data object Wallet : Screens("/wallet")
}