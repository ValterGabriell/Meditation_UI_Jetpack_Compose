package com.example.meditation_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.meditation_ui.ui.theme.Meditation_UI_Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meditation_UI_Theme {
                HomeScren()
            }
        }
    }
}

