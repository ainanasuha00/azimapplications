package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ExplorerPage
import com.example.ui.components.ExplorerSectionFooter
import com.example.ui.theme.ExplorerBorder
import com.example.ui.theme.ExplorerCardBg
import com.example.ui.theme.ExplorerCardBgSecondary
import com.example.ui.theme.ExplorerCoral
import com.example.ui.theme.ExplorerGold
import com.example.ui.theme.ExplorerInk
import com.example.ui.theme.ExplorerIvory
import com.example.ui.theme.ExplorerMuted
import com.example.ui.theme.ExplorerNavy
import com.example.ui.theme.ExplorerSage
import com.example.ui.theme.ExplorerTeal

@Composable
fun HomeScreen(
    onNavigate: (ExplorerPage) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

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
            // Minimalist Adventure Hero Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_banner"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = ExplorerNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Eyebrow
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = Color(0xFF234B5C),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "YEAR 4 ENGLISH",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 1.2.sp,
                                    color = Color(0xFFB9DDDD)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "• Asking & Giving Directions",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFD6EAE6),
                                fontSize = 12.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Explore the Town.\nFind Your Way!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            lineHeight = 34.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Hello, young explorers! Today, you will discover places in town, learn useful direction words and help a tourist find the right way.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFE5EEEE),
                            fontSize = 14.5.sp,
                            lineHeight = 22.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Minimalist adventure icon badges strip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF132F3B))
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🏫 School", fontSize = 12.sp, color = Color(0xFFD2E8E4))
                        Text("•", color = Color(0xFF4C7380))
                        Text("🏥 Hospital", fontSize = 12.sp, color = Color(0xFFD2E8E4))
                        Text("•", color = Color(0xFF4C7380))
                        Text("🧭 Directions", fontSize = 12.sp, color = ExplorerGold, fontWeight = FontWeight.Bold)
                        Text("•", color = Color(0xFF4C7380))
                        Text("🌳 Park", fontSize = 12.sp, color = Color(0xFFD2E8E4))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { onNavigate(ExplorerPage.ACTIVITY_1) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ExplorerCoral,
                            contentColor = ExplorerNavy
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("start_adventure_btn")
                    ) {
                        Text(
                            text = "START YOUR ADVENTURE",
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Go",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Section Heading: Learning Journey
            Column {
                Text(
                    text = "YOUR LEARNING JOURNEY",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.5.sp,
                        color = ExplorerMuted
                    )
                )
                Text(
                    text = "Three Fun Activities",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = ExplorerNavy
                    )
                )
                Text(
                    text = "Complete the activities in order. Read the instructions before you begin.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ExplorerMuted,
                        fontSize = 13.5.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Activity 1 Card
            ActivityOverviewCard(
                activityNumber = "ACTIVITY 1",
                emoji = "🗺️",
                title = "Discover, Learn & Listen",
                description = "Explore the town, learn place names and direction words, then listen and follow directions.",
                buttonText = "LET'S EXPLORE",
                onClick = { onNavigate(ExplorerPage.ACTIVITY_1) },
                testTag = "act_1_card"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Activity 2 Card
            ActivityOverviewCard(
                activityNumber = "ACTIVITY 2",
                emoji = "👀",
                title = "Where Am I?",
                description = "Read the clues and choose the correct place in town in the Wordwall game.",
                buttonText = "PLAY WORDWALL",
                onClick = { onNavigate(ExplorerPage.ACTIVITY_2) },
                testTag = "act_2_card"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Activity 3 Card
            ActivityOverviewCard(
                activityNumber = "ACTIVITY 3",
                emoji = "🧭",
                title = "Route Rescue: The Lost Tourist",
                description = "Use what you have learned to help a tourist find the right places.",
                buttonText = "PLAY GENIALLY",
                onClick = { onNavigate(ExplorerPage.ACTIVITY_3) },
                testTag = "act_3_card"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Learning Goal Panel
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("learning_goal_panel"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE8F4F2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = "Learning Goal",
                            tint = ExplorerTeal,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Our Learning Goal",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = ExplorerNavy
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "By the end of these activities, you should be able to name familiar places, understand simple directions and use direction words to guide someone.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = ExplorerInk,
                                fontSize = 13.5.sp,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        // Section Footer with required disclaimer & credentials
        ExplorerSectionFooter(sectionName = "home")
    }
}

@Composable
private fun ActivityOverviewCard(
    activityNumber: String,
    emoji: String,
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = emoji,
                    fontSize = 32.sp
                )
                Surface(
                    color = Color(0xFFF3F7F6),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ExplorerBorder)
                ) {
                    Text(
                        text = activityNumber,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = ExplorerTeal,
                            letterSpacing = 1.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = ExplorerNavy,
                    fontSize = 17.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = ExplorerMuted,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ExplorerTeal,
                    contentColor = ExplorerNavy
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
            ) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
