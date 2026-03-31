package com.example.screenshottesting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screenshottesting.ui.theme.AppTheme

/**
 * Content card with a leading icon, title, and subtitle.
 * Supports a loading skeleton state via [isLoading].
 *
 * @param title      Primary bold text (ignored when loading)
 * @param subtitle   Secondary descriptive text (ignored when loading)
 * @param icon       Optional leading Material icon (ignored when loading)
 * @param isLoading  When true renders placeholder skeleton instead of content
 */
@Composable
fun ContentCard(
    title: String,
    subtitle: String,
    icon: ImageVector?,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (isLoading) {
            LoadingSkeleton()
        } else {
            CardContent(title = title, subtitle = subtitle, icon = icon)
        }
    }
}

@Composable
private fun CardContent(
    title: String,
    subtitle: String,
    icon: ImageVector?
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LoadingSkeleton() {
    val placeholderColor = MaterialTheme.colorScheme.surfaceVariant
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon placeholder
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(placeholderColor)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            // Title placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(placeholderColor)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Subtitle placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(placeholderColor)
            )
        }
    }
}

@Preview(name = "ContentCard — Content Light", showBackground = true)
@Composable
private fun ContentCardPreview() {
    AppTheme(darkTheme = false) {
        ContentCard(
            title = "Card Title",
            subtitle = "Subtitle describing the card content",
            icon = Icons.Filled.Star,
            isLoading = false
        )
    }
}

@Preview(name = "ContentCard — Loading Light", showBackground = true)
@Composable
private fun LoadingCardPreview() {
    AppTheme(darkTheme = false) {
        ContentCard(
            title = "",
            subtitle = "",
            icon = null,
            isLoading = true
        )
    }
}
