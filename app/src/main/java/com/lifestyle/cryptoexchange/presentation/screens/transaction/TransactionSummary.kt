package com.lifestyle.cryptoexchange.presentation.screens.transaction

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.data.model.TransactionPortfolioData
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.CommonHeader
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.TransactionItem
import com.lifestyle.cryptoexchange.presentation.screens.transaction.component.SurfaceButton
import com.lifestyle.cryptoexchange.presentation.screens.transaction.viewmodel.TransactionViewModel
import com.lifestyle.cryptoexchange.ui.theme.GlossyColorFirst
import com.lifestyle.cryptoexchange.ui.theme.PrimaryGreen
import com.lifestyle.cryptoexchange.ui.theme.PrimarySelector

@Composable
fun TransactionSummary(transactionViewModel: TransactionViewModel, onNavigateExchange: () -> Unit) {
    val transactions by transactionViewModel.transactions.collectAsState()
    val transactionPortfolioData by transactionViewModel.transactionsPortfolio.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier.height(78.dp)) }
            item {
                CommonHeader(
                    horizontalPadding = 9.dp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PortfolioValueCard(transactionPortfolioData) }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                SendReceiveButtonGroup {
                    onNavigateExchange.invoke()
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { TransactionHistory(transactions) }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            GlossyColorFirst.copy(alpha = 0.3f),
                            GlossyColorFirst.copy(alpha = 0.3f)
                        )
                    )
                )
        )
    }
}

@Composable
fun TransactionHistory(transactions: List<Transactions>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.transactions),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.transaction_duration),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        transactions.forEach {
            TransactionItem(it)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun SendReceiveButtonGroup(onNavigateExchange: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SurfaceButton(R.drawable.arrow_up){
            Toast.makeText(context, "Action Sent", Toast.LENGTH_SHORT).show()
        }
        Spacer(modifier = Modifier.width(12.dp))
        SurfaceButton(R.drawable.plus) {
            onNavigateExchange.invoke()
        }
        Spacer(modifier = Modifier.width(12.dp))
        SurfaceButton(R.drawable.arrow_down){
            Toast.makeText(context, "Action Received", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun PortfolioValueCard(transactionPortfolioData: TransactionPortfolioData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        PortFolioValueItem(
            modifier = Modifier
                .offset(y = (45).dp)
                .scale(scaleX = 0.8f, scaleY = 0.8f),
            transactionPortfolioData = transactionPortfolioData

        )
        PortFolioValueItem(
            modifier = Modifier
                .offset(y = (25).dp)
                .scale(scaleX = 0.9f, scaleY = 0.9f),
            transactionPortfolioData = transactionPortfolioData

        )
        PortFolioValueItem(transactionPortfolioData = transactionPortfolioData)
    }
}

@Composable
fun PortFolioValueItem(
    modifier: Modifier = Modifier,
    transactionPortfolioData: TransactionPortfolioData,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.gradient_background),
                contentDescription = "Gradient Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationX = 180F }
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .height(34.dp)
                        .width(56.dp),
                    color = PrimarySelector,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = transactionPortfolioData.amountType,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = transactionPortfolioData.totalAmount,
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = transactionPortfolioData.amount,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = transactionPortfolioData.increasePercent,
                        fontSize = 14.sp,
                        color = PrimaryGreen
                    )
                }
            }
        }
    }
}
