package com.example.screenshottesting.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.screenshottesting.ui.theme.AppTheme

/**
 * App top bar with an optional back-navigation arrow.
 *
 * @param title Screen title shown in the bar
 * @param onNavigateBack If non-null, shows a back arrow and invokes this callback on click
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onNavigateBack: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onNavigateBack != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(name = "AppTopBar — With Back Arrow", showBackground = true)
@Composable
private fun TopBarWithBackPreview() {
    AppTheme(darkTheme = false) {
        AppTopBar(title = "Screen Title", onNavigateBack = {})
    }
}

@Preview(name = "AppTopBar — No Back Arrow", showBackground = true)
@Composable
private fun TopBarNoBackPreview() {
    AppTheme(darkTheme = false) {
        AppTopBar(title = "Home", onNavigateBack = null)
    }
}
