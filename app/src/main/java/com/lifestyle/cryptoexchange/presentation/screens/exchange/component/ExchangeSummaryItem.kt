package com.lifestyle.cryptoexchange.presentation.screens.exchange.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifestyle.cryptoexchange.presentation.screens.exchange.model.ExchangeSummaryUiModel

@Composable
fun ExchangeSummaryItem(item: ExchangeSummaryUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.title, color = MaterialTheme.colorScheme.outline, fontSize = 14.sp)
        Text(text = item.value, color = MaterialTheme.colorScheme.outline, fontSize = 14.sp)
    }
}