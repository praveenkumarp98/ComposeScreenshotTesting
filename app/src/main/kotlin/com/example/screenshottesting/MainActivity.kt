package com.example.screenshottesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.screenshottesting.components.AppTopBar
import com.example.screenshottesting.components.ContentCard
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Screenshot Testing Demo",
                            onNavigateBack = null
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ContentCard(
                            title = "Compose Preview Screenshot Testing",
                            subtitle = "Host-side tests powered by Layoutlib — no emulator needed",
                            icon = Icons.Filled.Info
                        )
                        PrimaryButton(
                            label = "Run Screenshot Tests",
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}
