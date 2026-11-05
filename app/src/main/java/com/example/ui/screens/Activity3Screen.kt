package com.example.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.ExplorerRepository
import com.example.tts.SpeechManager
import com.example.ui.components.ExplorerPage
import com.example.ui.components.ExplorerSectionFooter
import com.example.ui.theme.ExplorerBorder
import com.example.ui.theme.ExplorerCoral
import com.example.ui.theme.ExplorerGold
import com.example.ui.theme.ExplorerInk
import com.example.ui.theme.ExplorerIvory
import com.example.ui.theme.ExplorerMuted
import com.example.ui.theme.ExplorerNavy
import com.example.ui.theme.ExplorerSage
import com.example.ui.theme.ExplorerTeal

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Activity3Screen(
    speechManager: SpeechManager,
    onNavigate: (ExplorerPage) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val geniallyUrl = "https://view.genially.com/6aebd5b058c0b5120094a107"
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }
    var isLoadingWeb by remember { mutableStateOf(true) }
    var showSimulator by remember { mutableStateOf(false) }

    // Simulator state
    var missionIndex by remember { mutableIntStateOf(0) }
    val missionAnswers = remember { mutableStateMapOf<Int, Int>() }
    var missionFeedback by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ExplorerIvory)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Eyebrow & Title
            Surface(
                color = Color(0xFFE8F2F0),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder)
            ) {
                Text(
                    text = "ACTIVITY 3 OF 3",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = ExplorerTeal,
                        letterSpacing = 1.2.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Route Rescue: The Lost Tourist",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = ExplorerNavy
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "A tourist is lost in our town! Become a helpful guide and help the tourist find the right places.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = ExplorerMuted,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Instructions Box
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9E8)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0DE9F)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "How to play",
                            tint = Color(0xFFC79222),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "How to play",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("1. First, read the mission carefully.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("2. Next, look at the map and the choices on the screen.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("3. Then, choose the correct route to help the tourist.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("4. Complete each challenge and try to rescue the tourist. Good luck, explorer!", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Embed Section Wrapper
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("genially_container")
            ) {
                Column {
                    // Embed Top Bar
                    Surface(
                        color = Color(0xFFEDF5F2),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Genially: Route Rescue – The Lost Tourist",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = ExplorerNavy
                                    )
                                )
                                Text(
                                    text = "Use your direction words to guide the tourist.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = ExplorerMuted,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { webViewInstance?.reload() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "Reload",
                                        tint = ExplorerNavy
                                    )
                                }
                                Button(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geniallyUrl))
                                        context.startActivity(intent)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ExplorerCoral,
                                        contentColor = ExplorerNavy
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.height(38.dp)
                                ) {
                                    Text("OPEN", fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }

                    // WebView Frame
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(520.dp)
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                WebView(ctx).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    settings.apply {
                                        javaScriptEnabled = true
                                        domStorageEnabled = true
                                        loadWithOverviewMode = true
                                        useWideViewPort = true
                                        cacheMode = WebSettings.LOAD_DEFAULT
                                    }
                                    webViewClient = object : WebViewClient() {
                                        override fun onPageFinished(view: WebView?, url: String?) {
                                            super.onPageFinished(view, url)
                                            isLoadingWeb = false
                                        }
                                    }
                                    webChromeClient = WebChromeClient()
                                    loadUrl(geniallyUrl)
                                    webViewInstance = this
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )

                        if (isLoadingWeb) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White.copy(alpha = 0.85f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(color = ExplorerTeal)
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Loading Genially Rescue Mission...",
                                        style = MaterialTheme.typography.bodySmall.copy(color = ExplorerNavy)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "If the game does not appear here, select “OPEN” above. Some interactive activities may open more reliably in a new browser tab.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = ExplorerMuted,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Bonus Route Rescue Interactive Missions
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Navigation,
                                contentDescription = "Route Simulation",
                                tint = ExplorerTeal,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Route Rescue Simulator",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = ExplorerNavy
                                )
                            )
                        }
                        OutlinedButton(
                            onClick = { showSimulator = !showSimulator },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (showSimulator) "Hide" else "Play Missions",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    AnimatedVisibility(visible = showSimulator) {
                        val currentMission = ExplorerRepository.rescueMissions[missionIndex]

                        Column(modifier = Modifier.padding(top = 14.dp)) {
                            // Tourist prompt card
                            Surface(
                                color = Color(0xFFF7FAF9),
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Mission ${missionIndex + 1} of ${ExplorerRepository.rescueMissions.size}: Lost Tourist",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = ExplorerTeal,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        IconButton(
                                            onClick = { speechManager.speak(currentMission.touristSaying) },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                                contentDescription = "Speak tourist dialog",
                                                tint = ExplorerNavy,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = currentMission.touristSaying,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = ExplorerInk,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Start: ${currentMission.startLocation}",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = ExplorerNavy
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text("➔", color = ExplorerTeal, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Destination: ${currentMission.destination}",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = ExplorerCoral
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = currentMission.prompt,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = ExplorerNavy
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            currentMission.routeOptions.forEachIndexed { optIdx, optText ->
                                val isSelected = missionAnswers[missionIndex] == optIdx
                                val isCorrect = isSelected && optIdx == currentMission.correctRouteIndex
                                val isWrong = isSelected && optIdx != currentMission.correctRouteIndex

                                Surface(
                                    onClick = {
                                        missionAnswers[missionIndex] = optIdx
                                        if (optIdx == currentMission.correctRouteIndex) {
                                            missionFeedback = "Super guide! 🧭 Correct route! ${currentMission.teacherTip}"
                                            speechManager.speak("Excellent! You guided the tourist safely!")
                                        } else {
                                            missionFeedback = "Not quite safe! Read the direction symbols again and choose the right road."
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    color = when {
                                        isCorrect -> Color(0xFFE3F7EB)
                                        isWrong -> Color(0xFFFFECE5)
                                        isSelected -> Color(0xFFD9EEEA)
                                        else -> Color.White
                                    },
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.5.dp,
                                        when {
                                            isCorrect -> Color(0xFF3DA668)
                                            isWrong -> ExplorerCoral
                                            isSelected -> ExplorerTeal
                                            else -> ExplorerBorder
                                        }
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Text(
                                        text = optText,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = ExplorerNavy,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 13.5.sp
                                        )
                                    )
                                }
                            }

                            missionFeedback?.let { fb ->
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    color = if (fb.startsWith("Super")) Color(0xFFE3F7EB) else Color(0xFFFFECE5),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = fb,
                                        modifier = Modifier.padding(10.dp),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = ExplorerNavy
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (missionIndex < ExplorerRepository.rescueMissions.size - 1) {
                                    Button(
                                        onClick = {
                                            missionIndex++
                                            missionFeedback = null
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = ExplorerTeal,
                                            contentColor = ExplorerNavy
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Next Rescue Mission →", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            missionIndex = 0
                                            missionAnswers.clear()
                                            missionFeedback = null
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = ExplorerTeal,
                                            contentColor = ExplorerNavy
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Restart Missions", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Completion Panel
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("completion_panel")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF3CD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stars,
                            contentDescription = "Explorer Star",
                            tint = Color(0xFFD49C14),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Well done, young explorer! 🎉",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerNavy,
                            fontSize = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "You have completed all three activities. Remember to use polite words and clear directions when helping someone find a place.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = ExplorerInk,
                            fontSize = 13.5.sp,
                            lineHeight = 20.sp
                        ),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onNavigate(ExplorerPage.HOME) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ExplorerNavy,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(44.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("BACK TO HOME", fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Section Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = { onNavigate(ExplorerPage.ACTIVITY_2) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("WORDWALL", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Button(
                    onClick = { onNavigate(ExplorerPage.HOME) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExplorerCoral,
                        contentColor = ExplorerNavy
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("FINISH HOME", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        // Section Footer with required disclaimer & credentials
        ExplorerSectionFooter(sectionName = "activity_3")
    }
}
