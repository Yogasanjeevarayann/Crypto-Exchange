package com.lifestyle.cryptoexchange.presentation.screens.analytics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.data.model.Asset
import com.lifestyle.cryptoexchange.data.model.Transactions
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.AssetCard
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.CommonHeader
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.ImageType
import com.lifestyle.cryptoexchange.presentation.screens.analytics.components.TransactionItem
import com.lifestyle.cryptoexchange.presentation.screens.analytics.viewmodel.AnalyticsViewModel
import com.lifestyle.cryptoexchange.ui.theme.GlossyColorFirst
import com.lifestyle.cryptoexchange.ui.theme.PrimaryGreen
import com.lifestyle.cryptoexchange.ui.theme.ToggleBackground
import com.lifestyle.cryptoexchange.ui.theme.TransactionBackground
import com.lifestyle.cryptoexchange.ui.theme.TransactionBackgroundLight


enum class Currency { INR, BTC }

@Composable
fun AnalyticsScreen(
    analyticsViewModel: AnalyticsViewModel,
    portfolioValue: String = "₹1,57,342.25"
) {
    val assets by analyticsViewModel.portfolioAsset.collectAsState()
    val recentTransactions by analyticsViewModel.recentTransactions.collectAsState()
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.surfaceContainerLowest
                )
        ) {
            item { PortfolioValueHeader(portfolioValue) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                PortfolioTrendGraph()
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { PortfolioAssets(assets) }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { RecentTransactions(recentTransactions) }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
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
fun PortfolioValueHeader(portfolioValue: String) {
    var selectedCurrency by remember { mutableStateOf(Currency.INR) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 9.dp)
            .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)),
        color = Color.Transparent
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.gradient_background_big),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationZ = 180F },
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(78.dp))
                CommonHeader(color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.portfolio_value),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(
                            painter = painterResource(R.drawable.right_arrow),
                            contentDescription = "Go to portfolio details",
                            tint = Color.White
                        )
                    }
                    CurrencyToggle(selectedCurrency) {
                        selectedCurrency = it
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = portfolioValue,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun RecentTransactions(recentTransactions: List<Transactions>) {
    Text(
        text = stringResource(R.string.recent_transactions),
        color = Color.White,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        recentTransactions.forEach {
            TransactionItem(it)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }

}

@Composable
fun PortfolioAssets(portfolioAssets: List<Asset>) {
    LazyRow {
        items(portfolioAssets.size) {
            AssetCard(portfolioAssets[it])
        }
    }
}


@Composable
fun CurrencyToggle(selectedCurrency: Currency, onUpdateCurrency: (Currency) -> Unit) {
    Row(
        modifier = Modifier
            .height(45.dp)
            .width(110.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(ToggleBackground.copy(alpha = 0.2f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconToggleButton(
            checked = selectedCurrency == Currency.INR,
            onCheckedChange = {
                onUpdateCurrency(Currency.INR)
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(30.dp))
                .background(if (selectedCurrency == Currency.INR) Color.Black else Color.Transparent),
            content = {
                Icon(
                    painter = painterResource(R.drawable.money),
                    contentDescription = "INR",
                    tint = if (selectedCurrency == Currency.INR) Color.White else Color.LightGray
                )
            }
        )
        IconToggleButton(
            checked = selectedCurrency == Currency.BTC,
            onCheckedChange = {
                onUpdateCurrency(Currency.BTC)
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(30.dp))
                .background(if (selectedCurrency == Currency.BTC) Color.Black else Color.Transparent),
            content = {
                Icon(
                    painter = painterResource(R.drawable.bitcoin),
                    contentDescription = "BTC",
                    tint = if (selectedCurrency == Currency.BTC) Color.White else Color.LightGray
                )
            }
        )
    }
}


enum class TimeSelector(val label: String) {
    ONE_HOUR("1h"), EIGHT_HOUR("1h"), ONE_DAY("1d"), ONE_WEEK("1w"), ONE_MONTH("1m"), SIX_MONTH("6m"), ONE_YEAR(
        "1y"
    )
}

@Composable
fun PortfolioTrendGraph() {
    var isTrendSelected by remember { mutableStateOf(TimeSelector.SIX_MONTH) }
    val items = listOf(
        TimeSelector.ONE_HOUR,
        TimeSelector.EIGHT_HOUR,
        TimeSelector.ONE_DAY,
        TimeSelector.ONE_WEEK,
        TimeSelector.ONE_MONTH,
        TimeSelector.SIX_MONTH,
        TimeSelector.ONE_YEAR
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
            Surface(
                modifier = Modifier.size(45.dp),
                onClick = {
                    isTrendSelected = it
                },
                shape = RoundedCornerShape(20.dp),
                color = if (isTrendSelected == it) Color.DarkGray else Color.Transparent
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it.label,
                        fontSize = 14.sp,
                        color = if (isTrendSelected == it) Color.White else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }

    val mockTrendData = mapOf(
        TimeSelector.ONE_HOUR to listOf(10f, 11.4f, 10.6f, 12.8f, 11.3f, 13.9f, 12.1f),
        TimeSelector.EIGHT_HOUR to listOf(9.7f, 10.9f, 11.6f, 10.4f, 12.9f, 12.2f, 13.5f),
        TimeSelector.ONE_DAY to listOf(10.1f, 11.5f, 10.7f, 13.3f, 12.1f, 14.5f, 13.2f),
        TimeSelector.ONE_WEEK to listOf(8.6f, 9.7f, 10.4f, 9.3f, 11.9f, 12.6f, 11.2f),
        TimeSelector.ONE_MONTH to listOf(9.2f, 10.5f, 11.3f, 10f, 13.2f, 14.1f, 12.8f),
        TimeSelector.SIX_MONTH to listOf(7.4f, 9.1f, 9.9f, 10.3f, 12.1f, 13.8f, 13.4f),
        TimeSelector.ONE_YEAR to listOf(6.5f, 8.7f, 9.3f, 10.8f, 12.4f, 13.6f, 14.8f)
    )


    val selectedData = mockTrendData[isTrendSelected] ?: emptyList()

    Spacer(modifier = Modifier.height(16.dp))
    val isDarkTheme = isSystemInDarkTheme()
    PortfolioTrendLineChart(
        dataPoints = selectedData,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp),
        isDarkTheme = isDarkTheme
    )
}

@Composable
fun PortfolioTrendLineChart(
    dataPoints: List<Float>,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean
) {
    Canvas(modifier = modifier) {
        if (dataPoints.isEmpty()) return@Canvas
        val horizontalPadding = 16.dp.toPx()
        val spacing = (size.width - 2 * horizontalPadding) / (dataPoints.size - 1)
        val maxY = dataPoints.maxOrNull() ?: 1f
        val scaledPoints = dataPoints.map {
            size.height - (it / maxY * size.height)
        }
        val barWidth = spacing * 0.7f
        val lineYOffset = 32.dp.toPx()

        for (i in dataPoints.indices) {
            val x = i * spacing + horizontalPadding - barWidth / 2
            val y = scaledPoints[i]
            val height = size.height - y

            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = if (isDarkTheme) {
                        listOf(Color.Black, TransactionBackground)
                    } else {
                        listOf(TransactionBackgroundLight, Color.White)
                    }
                ),
                topLeft = Offset(x, y),
                size = Size(barWidth, height),
                cornerRadius = CornerRadius(x = 12.dp.toPx(), y = 12.dp.toPx())
            )
        }

        val path = Path().apply {
            moveTo(horizontalPadding, scaledPoints.first() + lineYOffset)
            for (i in 1 until dataPoints.size) {
                val x1 = (i - 1) * spacing + horizontalPadding
                val y1 = scaledPoints[i - 1] + lineYOffset
                val x2 = i * spacing + horizontalPadding
                val y2 = scaledPoints[i] + lineYOffset
                val midX = (x1 + x2) / 2
                val midY = (y1 + y2) / 2
                quadraticTo(x1, y1, midX, midY)
            }

            lineTo(
                (dataPoints.size - 1) * spacing + horizontalPadding,
                scaledPoints.last() + lineYOffset
            )
        }

        drawPath(
            path = path,
            color = PrimaryGreen,
            style = Stroke(width = 4f, cap = StrokeCap.Round)
        )

    }
}

@Preview
@Composable
fun TransactionItemPreview() {
    TransactionItem(
        Transactions(
            R.drawable.bitcoin_image,
            "Receive",
            "BTC",
            "+0.002126",
            "20 March",
            ImageType.FULL
        ),
    )
}

@Preview
@Composable
fun AssetCartBTCPreview() {
    AssetCard(Asset(R.drawable.bitcoin_image, "Bitcoin (BTC)", "₹ 75,62,502.14", "+3.2%"))
}

@Preview
@Composable
fun AssetCartETHPreview() {
    AssetCard(Asset(R.drawable.ethereum_image, "Ether (ETH)", "₹ 1,79,102.50", "+3.4%"))
}
