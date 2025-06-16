package com.example.khabbar.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import java.net.ConnectException
import java.net.SocketTimeoutException
import com.example.khabbar.R


@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    // Determine message and icon based on error state
    val (message, icon) = when {
        error != null -> {
            val errorMsg = when (error.error) {
                is SocketTimeoutException -> "Server Unavailable"
                is ConnectException -> "Internet Unavailable"
                else -> "Unknown Error"
            }
            errorMsg to R.drawable.ic_network_error
        }
        else -> "Empty Screen" to R.drawable.ic_search_document
    }

    // Fade-in animation
    val alpha by animateFloatAsState(
        targetValue = 0.3f,
        animationSpec = tween(durationMillis = 1000)
    )

    // Layout
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Empty state icon",
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alpha)
        )
    }
}