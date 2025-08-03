package com.lifestyle.cryptoexchange.presentation.screens.analytics.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lifestyle.cryptoexchange.R

@Composable
fun CommonHeader(horizontalPadding: Dp = 0.dp, color: Color) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = horizontalPadding + 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.menu),
            tint = color,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Action Menu", Toast.LENGTH_SHORT).show()
            },
            contentDescription = "Menu",
        )
        Icon(
            painter = painterResource(R.drawable.notification),
            tint = color,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Action Notification", Toast.LENGTH_SHORT).show()
            },
            contentDescription = "Notifications",
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CommonHeaderPreview() {
    CommonHeader(color = Color.Black)
}