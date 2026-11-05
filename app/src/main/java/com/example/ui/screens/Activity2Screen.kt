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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
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
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Activity2Screen(
    onNavigate: (ExplorerPage) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val wordwallUrl = "https://wordwall.net/resource/123111487?wwmethod=link"
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }
    var isLoadingWeb by remember { mutableStateOf(true) }
    var showInAppPractice by remember { mutableStateOf(false) }

    // Native Practice Quiz state
    var currentClueIdx by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var clueFeedback by remember { mutableStateOf<String?>(null) }
    var clueScore by remember { mutableIntStateOf(0) }

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
                    text = "ACTIVITY 2 OF 3",
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
                text = "Where Am I?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = ExplorerNavy
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Let's play the Wordwall game! Read each clue and find the correct place in town.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = ExplorerMuted,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Instruction Card
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
                    Text("1. First, click the Start button in the game below.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("2. Next, read the clue carefully.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("3. Then, choose the place that matches the clue.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("4. Keep going until you finish the game. Do your best!", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
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
                    .testTag("wordwall_container")
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
                                    text = "Wordwall: Where Am I?",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = ExplorerNavy
                                    )
                                )
                                Text(
                                    text = "Read the clue and choose the correct answer.",
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
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wordwallUrl))
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
                                    loadUrl(wordwallUrl)
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
                                        text = "Loading Wordwall Activity...",
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
                text = "If the game does not appear here, select \"OPEN\" above. The game may open in a new browser tab.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = ExplorerMuted,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Bonus In-App Clues Practice Mode
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
                                imageVector = Icons.Default.Quiz,
                                contentDescription = "Practice Quiz",
                                tint = ExplorerTeal,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Interactive Clue Cards",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = ExplorerNavy
                                )
                            )
                        }
                        OutlinedButton(
                            onClick = { showInAppPractice = !showInAppPractice },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (showInAppPractice) "Hide" else "Practice Here",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    AnimatedVisibility(visible = showInAppPractice) {
                        Column(modifier = Modifier.padding(top = 14.dp)) {
                            val clue = ExplorerRepository.whereAmIClues[currentClueIdx]

                            Surface(
                                color = Color(0xFFF7FAF9),
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(
                                        text = "Clue ${currentClueIdx + 1} of ${ExplorerRepository.whereAmIClues.size}:",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = ExplorerTeal,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "\"${clue.clue}\"",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = ExplorerInk,
                                            lineHeight = 20.sp
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                clue.options.forEach { opt ->
                                    val isSelected = selectedOption == opt
                                    Surface(
                                        onClick = {
                                            selectedOption = opt
                                            if (opt == clue.answer) {
                                                clueScore++
                                                clueFeedback = "Correct! 🌟 ${clue.explanation}"
                                            } else {
                                                clueFeedback = "Try again! Look closely at the clue details."
                                            }
                                        },
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isSelected) Color(0xFFD9EEEA) else Color.White,
                                        border = androidx.compose.foundation.BorderStroke(
                                            1.5.dp,
                                            if (isSelected) ExplorerTeal else ExplorerBorder
                                        )
                                    ) {
                                        Text(
                                            text = opt,
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = ExplorerNavy
                                            )
                                        )
                                    }
                                }
                            }

                            clueFeedback?.let { fb ->
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    color = if (fb.startsWith("Correct")) Color(0xFFE3F7EB) else Color(0xFFFFECE5),
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
                                if (currentClueIdx < ExplorerRepository.whereAmIClues.size - 1) {
                                    Button(
                                        onClick = {
                                            currentClueIdx++
                                            selectedOption = null
                                            clueFeedback = null
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = ExplorerTeal,
                                            contentColor = ExplorerNavy
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Next Clue →", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            currentClueIdx = 0
                                            selectedOption = null
                                            clueFeedback = null
                                            clueScore = 0
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = ExplorerTeal,
                                            contentColor = ExplorerNavy
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Restart Practice", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
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
                    onClick = { onNavigate(ExplorerPage.ACTIVITY_1) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("ACT 1", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Button(
                    onClick = { onNavigate(ExplorerPage.ACTIVITY_3) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExplorerTeal,
                        contentColor = ExplorerNavy
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("ROUTE RESCUE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next", modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        // Section Footer with required disclaimer & credentials
        ExplorerSectionFooter(sectionName = "activity_2")
    }
}
