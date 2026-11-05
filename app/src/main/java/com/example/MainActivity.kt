package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.tts.rememberSpeechManager
import com.example.ui.components.ExplorerHeader
import com.example.ui.components.ExplorerPage
import com.example.ui.screens.Activity1Screen
import com.example.ui.screens.Activity2Screen
import com.example.ui.screens.Activity3Screen
import com.example.ui.screens.HomeScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ExplorerApp()
            }
        }
    }
}

@Composable
fun ExplorerApp() {
    var currentPage by remember { mutableStateOf(ExplorerPage.HOME) }
    val speechManager = rememberSpeechManager()

    // Handle system back button gracefully
    BackHandler(enabled = currentPage != ExplorerPage.HOME) {
        when (currentPage) {
            ExplorerPage.ACTIVITY_3 -> currentPage = ExplorerPage.ACTIVITY_2
            ExplorerPage.ACTIVITY_2 -> currentPage = ExplorerPage.ACTIVITY_1
            ExplorerPage.ACTIVITY_1 -> currentPage = ExplorerPage.HOME
            ExplorerPage.HOME -> { /* system default */ }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("explorer_app_scaffold"),
        topBar = {
            ExplorerHeader(
                currentPage = currentPage,
                onNavigate = { page ->
                    speechManager.stop()
                    currentPage = page
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "page_transition"
            ) { page ->
                when (page) {
                    ExplorerPage.HOME -> HomeScreen(
                        onNavigate = { target ->
                            speechManager.stop()
                            currentPage = target
                        }
                    )
                    ExplorerPage.ACTIVITY_1 -> Activity1Screen(
                        speechManager = speechManager,
                        onNavigate = { target ->
                            speechManager.stop()
                            currentPage = target
                        }
                    )
                    ExplorerPage.ACTIVITY_2 -> Activity2Screen(
                        onNavigate = { target ->
                            speechManager.stop()
                            currentPage = target
                        }
                    )
                    ExplorerPage.ACTIVITY_3 -> Activity3Screen(
                        speechManager = speechManager,
                        onNavigate = { target ->
                            speechManager.stop()
                            currentPage = target
                        }
                    )
                }
            }
        }
    }
}
