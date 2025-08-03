package com.lifestyle.cryptoexchange.presentation.screens.analytics.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.ui.theme.SurfaceGray

enum class ImageType {
    FULL, HALF_CENTER, HALF_ROUND
}

@Composable
fun TransactionItem(recentTransactionItem: Transactions) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            when (recentTransactionItem.imageType) {
                ImageType.FULL -> {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(0.dp),
                        painter = painterResource(recentTransactionItem.image),
                        contentDescription = "${recentTransactionItem.transactionStatus} ${recentTransactionItem.amount}"
                    )
                }

                ImageType.HALF_CENTER -> {
                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        color = SurfaceGray
                    ) {
                        Image(
                            modifier = Modifier.padding(12.dp),
                            painter = painterResource(recentTransactionItem.image),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "${recentTransactionItem.transactionStatus} ${recentTransactionItem.amount}"
                        )
                    }
                }

                ImageType.HALF_ROUND -> {

                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        color = SurfaceGray
                    ) {
                        Image(
                            modifier = Modifier.padding(12.dp),
                            painter = painterResource(recentTransactionItem.image),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "${recentTransactionItem.transactionStatus} ${recentTransactionItem.amount}"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recentTransactionItem.transactionStatus,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = recentTransactionItem.amountType,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recentTransactionItem.date,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = recentTransactionItem.amount,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun TransactionItemPreviewFull() {
    TransactionItem(
        Transactions(
            R.drawable.bitcoin_image,
            "Receive",
            "BTC",
            "+0.002126",
            "20 March",
            ImageType.FULL
        )
    )
}

@Preview
@Composable
fun TransactionItemPreviewHalfCenter() {
    TransactionItem(
        Transactions(
            R.drawable.self,
            "Receive",
            "BTC",
            "+0.002126",
            "20 March"
        )
    )
}

@Preview
@Composable
fun TransactionItemPreviewHalfRound() {
    TransactionItem(
        Transactions(
            R.drawable.arrow_up,
            "Sent",
            "ETH",
            "+0.002126",
            "19 March", ImageType.HALF_ROUND

        )
    )
}
