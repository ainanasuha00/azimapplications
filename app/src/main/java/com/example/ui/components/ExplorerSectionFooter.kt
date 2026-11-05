package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ExplorerBorder
import com.example.ui.theme.ExplorerCoral
import com.example.ui.theme.ExplorerGold
import com.example.ui.theme.ExplorerInk
import com.example.ui.theme.ExplorerIvory
import com.example.ui.theme.ExplorerMuted
import com.example.ui.theme.ExplorerNavy
import com.example.ui.theme.ExplorerTeal

/**
 * Section footer required on each section of The Explorer's Map.
 * Matches the minimalist adventure & direction aesthetic with exact educational credits and disclaimer.
 */
@Composable
fun ExplorerSectionFooter(
    modifier: Modifier = Modifier,
    sectionName: String? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("section_footer_${sectionName ?: "general"}"),
        color = ExplorerNavy,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Directional Compass Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF224B5C)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = "Explorer Compass",
                        tint = ExplorerGold,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "THE EXPLORER'S MAP",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp,
                        color = Color.White
                    )
                )
            }

            // Tagline
            Text(
                text = "Learning English, one adventure at a time.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFFD8E7E4),
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Author & Academic Institution
            Surface(
                color = Color(0xFF1E4657),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2E5E73))
            ) {
                Text(
                    text = "AZIM ZAID · INSTITUT PENDIDIKAN GURU KAMPUS KOTA BHARU",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = ExplorerGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        fontSize = 11.5.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Copyright notice
            Text(
                text = "© 2026 The Explorer's Map. All rights reserved.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFB5CCC8),
                    fontSize = 12.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 4.dp),
                color = Color(0xFF2A5364),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Educational Disclaimer Box
            Surface(
                color = Color(0xFF132F3B),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF224E60)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Disclaimer Information",
                        tint = ExplorerCoral,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Disclaimer: This website is made for educational and informational purposes only.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFFE2EBE9),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    )
                }
            }
        }
    }
}
