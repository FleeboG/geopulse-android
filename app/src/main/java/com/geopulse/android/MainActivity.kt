package com.geopulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.geopulse.android.navigation.AppNavHost
import com.geopulse.android.ui.theme.GeoPulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoPulseTheme {
                AppNavHost()
            }
        }
    }
}