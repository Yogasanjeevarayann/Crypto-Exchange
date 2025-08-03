package com.lifestyle.cryptoexchange.presentation.screens.exchange

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifestyle.cryptoexchange.R
import com.lifestyle.cryptoexchange.presentation.screens.exchange.component.ExchangeSummaryItem
import com.lifestyle.cryptoexchange.presentation.screens.exchange.model.ExchangeSummaryUiModel
import com.lifestyle.cryptoexchange.ui.theme.BrandSecondary
import com.lifestyle.cryptoexchange.ui.theme.GlossyColorFirst
import com.lifestyle.cryptoexchange.ui.theme.GlossyColorSecond
import com.lifestyle.cryptoexchange.ui.theme.SurfaceGray
import com.lifestyle.cryptoexchange.ui.theme.TransactionBackground

@Composable
fun ExchangeScreen(navigateUp: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            item { Spacer(modifier = Modifier.height(58.dp)) }
            item {
                ExchangeHeader {
                    navigateUp.invoke()
                }
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { SwapUI() }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    onClick = {
                        Toast.makeText(context, "Action Exchange", Toast.LENGTH_SHORT).show()
                    },
                    color = BrandSecondary, shape = RoundedCornerShape(30.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Exchange",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item { ExchangeSummary() }

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
fun ExchangeSummary() {
    val summaryList = listOf(
        ExchangeSummaryUiModel("Rate", "1 ETH = ₹ 1,76,138.80"),
        ExchangeSummaryUiModel("Spread", "0.2%"),
        ExchangeSummaryUiModel("Gas fee", "₹ 422.73"),
        ExchangeSummaryUiModel("You will receive", "₹ 1,75,716.07")
    )
    summaryList.forEach {
        ExchangeSummaryItem(it)
    }
}

@Composable
fun SwapUI() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                color = Color.Transparent,
                shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(
                                        RoundedCornerShape(50.dp)
                                    )
                                    .background(Color.White)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ethereum_image),
                                    contentDescription = "ETH"
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ETH",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                painter = painterResource(R.drawable.down_arrow),
                                contentDescription = "Down",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(text = "Send", fontSize = 14.sp, color = Color.White)

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "2.640",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Balance",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = "10.254",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                color = Color.Transparent,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(
                                        RoundedCornerShape(50.dp)
                                    )
                                    .background(SurfaceGray),
                                color = Color.Transparent
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = painterResource(R.drawable.currency),
                                    contentDescription = "INR",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "INR",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                painter = painterResource(R.drawable.down_arrow),
                                contentDescription = "Down",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Receive",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "₹ 4,65,006.44",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Balance",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = "4,35,804",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
        Surface(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.surfaceContainer,
                            MaterialTheme.colorScheme.surfaceContainerLowest
                        )
                    )
                ),
            onClick = {
                Toast.makeText(context, "Action Swap", Toast.LENGTH_SHORT).show()
            },
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(44.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceDim,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(R.drawable.resource_switch),
                    contentDescription = "Switch",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun ExchangeHeader(navigateUp: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .size(50.dp)
                .padding(16.dp)
                .clickable { navigateUp.invoke() }, color = Color.Transparent
        ) {
            Icon(
                painter = painterResource(R.drawable.back_arrow),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface
            )

        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Exchange", color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp)
    }
}
