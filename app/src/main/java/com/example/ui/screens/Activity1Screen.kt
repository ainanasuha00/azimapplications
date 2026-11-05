package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DirectionWord
import com.example.data.ExplorerRepository
import com.example.data.TownPlace
import com.example.tts.SpeechManager
import com.example.ui.components.ExplorerPage
import com.example.ui.components.ExplorerSectionFooter
import com.example.ui.theme.ExplorerBorder
import com.example.ui.theme.ExplorerCardBgSecondary
import com.example.ui.theme.ExplorerCoral
import com.example.ui.theme.ExplorerGold
import com.example.ui.theme.ExplorerInk
import com.example.ui.theme.ExplorerIvory
import com.example.ui.theme.ExplorerMuted
import com.example.ui.theme.ExplorerNavy
import com.example.ui.theme.ExplorerSage
import com.example.ui.theme.ExplorerTeal

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Activity1Screen(
    speechManager: SpeechManager,
    onNavigate: (ExplorerPage) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var selectedPlace by remember { mutableStateOf(ExplorerRepository.townPlaces[0]) }
    var selectedDirection by remember { mutableStateOf<DirectionWord?>(null) }

    // Listening question answers (question index -> option index)
    val userAnswers = remember { mutableStateMapOf<Int, Int>() }
    var scoreResult by remember { mutableStateOf<String?>(null) }
    var isScoreSubmitted by remember { mutableStateOf(false) }

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
            // Header Tag & Title
            Surface(
                color = Color(0xFFE8F2F0),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder)
            ) {
                Text(
                    text = "ACTIVITY 1 OF 3",
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
                text = "Discover, Learn & Listen",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = ExplorerNavy
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Let's explore our town and learn how to give directions. Complete the three small steps below.",
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
                            contentDescription = "Instructions",
                            tint = Color(0xFFC79222),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "How to complete this activity",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("1. First, look carefully at the town map and find the places you know.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("2. Next, choose a building to learn its name and what people do there.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("3. Then, read and say the direction words aloud.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                    Text("4. Finally, listen to the directions and answer the questions.", style = MaterialTheme.typography.bodySmall.copy(color = ExplorerInk))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // STEP 1: Discover the Town
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = ExplorerTeal,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "STEP 1",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Discover the Town",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerNavy
                        )
                    )
                }
                Text(
                    text = "Tap a place on the map to hear its name and learn what it is.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ExplorerMuted,
                        fontSize = 13.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Town Grid (2 columns on mobile)
            val chunkedPlaces = ExplorerRepository.townPlaces.chunked(2)
            chunkedPlaces.forEach { rowPlaces ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowPlaces.forEach { place ->
                        val isSelected = place.id == selectedPlace.id
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    selectedPlace = place
                                    speechManager.speak("${place.name}. ${place.description}")
                                }
                                .testTag("place_${place.id}"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) Color(0xFFEDF7F4) else Color.White
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                if (isSelected) 2.dp else 1.dp,
                                if (isSelected) ExplorerTeal else ExplorerBorder
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = place.emoji, fontSize = 32.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = place.name,
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = ExplorerNavy
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Place details card with TTS pronounce button
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF5F2)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD4E7E2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("place_details_card")
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedPlace.emoji, fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = selectedPlace.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = selectedPlace.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = ExplorerInk,
                                lineHeight = 18.sp
                            )
                        )
                    }
                    IconButton(
                        onClick = {
                            speechManager.speak("${selectedPlace.name}. ${selectedPlace.description}")
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ExplorerTeal)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Speak place",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // STEP 2: Learn the Direction Words
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = ExplorerTeal,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "STEP 2",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Learn Direction Words",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerNavy
                        )
                    )
                }
                Text(
                    text = "Look at each symbol. Tap to hear how to say it aloud.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ExplorerMuted,
                        fontSize = 13.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            val chunkedDirections = ExplorerRepository.directionWords.chunked(2)
            chunkedDirections.forEach { rowWords ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowWords.forEach { dirWord ->
                        val isSelected = selectedDirection?.id == dirWord.id
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    selectedDirection = dirWord
                                    speechManager.speak("${dirWord.phrase}. ${dirWord.meaning}")
                                }
                                .testTag("dir_${dirWord.id}"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) Color(0xFFEDF7F4) else Color.White
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                if (isSelected) 2.dp else 1.dp,
                                if (isSelected) ExplorerTeal else ExplorerBorder
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = dirWord.symbol,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            fontWeight = FontWeight.Black,
                                            color = ExplorerTeal
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = "Listen",
                                        tint = ExplorerMuted,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = dirWord.phrase,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = ExplorerNavy
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = dirWord.meaning,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = ExplorerMuted,
                                        fontSize = 11.5.sp,
                                        lineHeight = 16.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // STEP 3: Listen & Navigate
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = ExplorerTeal,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "STEP 3",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Listen & Navigate",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerNavy
                        )
                    )
                }
                Text(
                    text = "Listen carefully to the audio directions. You can play them again.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ExplorerMuted,
                        fontSize = 13.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Audio Player Box
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF5F2)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD4E6E1)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("audio_box")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(ExplorerTeal),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Hearing,
                                contentDescription = "Listen",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Directions Audio",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"${ExplorerRepository.listeningScript}\"",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = ExplorerInk,
                            lineHeight = 22.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                speechManager.speak(ExplorerRepository.listeningScript)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ExplorerTeal,
                                contentColor = ExplorerNavy
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("play_directions_btn")
                        ) {
                            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("PLAY DIRECTIONS", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }

                        OutlinedButton(
                            onClick = {
                                speechManager.speak(ExplorerRepository.listeningScript)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = ExplorerNavy)
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Replay", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("REPLAY", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }

                        if (speechManager.isSpeaking) {
                            IconButton(
                                onClick = { speechManager.stop() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Stop,
                                    contentDescription = "Stop audio",
                                    tint = ExplorerCoral
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = speechManager.statusMessage,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = ExplorerMuted,
                            fontSize = 11.5.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Questions 1, 2, 3
            ExplorerRepository.listeningQuestions.forEachIndexed { qIdx, question ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = question.question,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            question.options.forEachIndexed { optIdx, option ->
                                val isSelected = userAnswers[qIdx] == optIdx
                                val isCorrect = isScoreSubmitted && optIdx == question.correctIndex
                                val isWrong = isScoreSubmitted && isSelected && optIdx != question.correctIndex

                                val bgColor = when {
                                    isCorrect -> Color(0xFFE3F7EB)
                                    isWrong -> Color(0xFFFFECE5)
                                    isSelected -> Color(0xFFD9EEEA)
                                    else -> Color.White
                                }
                                val borderColor = when {
                                    isCorrect -> Color(0xFF3DA668)
                                    isWrong -> ExplorerCoral
                                    isSelected -> ExplorerTeal
                                    else -> ExplorerBorder
                                }

                                Surface(
                                    onClick = {
                                        userAnswers[qIdx] = optIdx
                                        scoreResult = null
                                        isScoreSubmitted = false
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    color = bgColor,
                                    border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
                                ) {
                                    Text(
                                        text = option,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = ExplorerNavy,
                                            fontSize = 13.5.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Check Answers Button
            Button(
                onClick = {
                    if (userAnswers.size < ExplorerRepository.listeningQuestions.size) {
                        scoreResult = "Please answer all three questions first."
                        isScoreSubmitted = false
                    } else {
                        var correctCount = 0
                        ExplorerRepository.listeningQuestions.forEachIndexed { index, question ->
                            if (userAnswers[index] == question.correctIndex) {
                                correctCount++
                            }
                        }
                        isScoreSubmitted = true
                        scoreResult = if (correctCount == 3) {
                            "Excellent! You followed all the directions correctly! 🌟 Well done, Explorer!"
                        } else {
                            "Good try! You got $correctCount out of 3. Listen again and try once more."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ExplorerCoral,
                    contentColor = ExplorerNavy
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("check_listening_btn")
            ) {
                Text(
                    text = "CHECK MY ANSWERS",
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp
                )
            }

            scoreResult?.let { msg ->
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = if (isScoreSubmitted && userAnswers.size == 3 && msg.startsWith("Excellent")) Color(0xFFE3F7EB) else Color(0xFFFFF2EC),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = msg,
                        modifier = Modifier.padding(14.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerNavy
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Navigation between sections
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = { onNavigate(ExplorerPage.HOME) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("HOME", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Button(
                    onClick = { onNavigate(ExplorerPage.ACTIVITY_2) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExplorerTeal,
                        contentColor = ExplorerNavy
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("WORDWALL", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next", modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        // Section Footer with required disclaimer & credentials
        ExplorerSectionFooter(sectionName = "activity_1")
    }
}
