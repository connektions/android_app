package com.example.connektions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.connektions.data.model.UserProject
import com.example.connektions.data.repository.ProfileRepository
import com.example.connektions.ui.components.ProfileCardItem
import com.example.connektions.ui.screens.ProfileScreen
import com.example.connektions.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    val profileRepo = ProfileRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                val scrollState = rememberScrollState()
                Scaffold(
                    topBar = {
                        CollapsingTopAppBar(scrollState.value.toFloat())
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = Color(0xFFF0F0F3),
                    ) {
                        ProfileScreen(profileRepo)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name!")
}
