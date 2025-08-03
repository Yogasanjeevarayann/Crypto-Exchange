package com.lifestyle.cryptoexchange.presentation.screens.transaction.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SurfaceButton(icon: Int, onclick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainer,
                    )
                )
            ),
        onClick = { onclick.invoke() },
        shape = RoundedCornerShape(50.dp),
        color = Color.Transparent,
        border = BorderStroke(
            0.5.dp, brush = Brush.verticalGradient(
                listOf(Color.LightGray, Color.Black)
            )
        )
    ) {
        Icon(
            painter = painterResource(icon),
            modifier = Modifier.padding(18.dp),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }
}
