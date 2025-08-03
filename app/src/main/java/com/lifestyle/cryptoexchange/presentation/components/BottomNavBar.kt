package com.lifestyle.cryptoexchange.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.presentation.navigation.NavItem
import com.lifestyle.cryptoexchange.presentation.navigation.Screens
import com.lifestyle.cryptoexchange.ui.theme.BrandSecondary
import com.lifestyle.cryptoexchange.ui.theme.PrimarySelector

@Composable
fun BottomNavBar(items: List<NavItem>, currentDestination: String?, navController: NavController) {
    val isAnalytics = currentDestination?.startsWith(Screens.Analytics.route) ?: false
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .height(64.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.8f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { screen ->
                    val isSelected = currentDestination?.startsWith(screen.route.route) ?: false
                    Surface(
                        onClick = {
                            navController.navigate(screen.route.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true

                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(30.dp)),
                        color = if (isSelected) PrimarySelector else Color.Transparent
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                painter = painterResource(screen.icon),
                                modifier = Modifier.padding(top = 10.dp),
                                tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                contentDescription = screen.label
                            )
                            Text(
                                text = screen.label,
                                color = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = if (isAnalytics) 9.sp else 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
            if (isAnalytics) {
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    modifier = Modifier.size(60.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(50.dp),
                    onClick = {
                        navController.navigate(Screens.Exchange.route)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        tint = BrandSecondary
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewBottomNavBar() {
    val navItems = listOf(
        NavItem("Analytics", Screens.Analytics, R.drawable.analytics),
        NavItem("Exchange", Screens.Exchange, R.drawable.exchange),
        NavItem("Record", Screens.Record, R.drawable.chart),
        NavItem("Wallet", Screens.Wallet, R.drawable.wallet),
    )
    BottomNavBar(navItems, "/analytics", rememberNavController())
}
