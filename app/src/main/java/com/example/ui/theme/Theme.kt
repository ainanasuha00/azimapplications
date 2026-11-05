package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = ExplorerNavy,
    onPrimary = Color.White,
    primaryContainer = ExplorerTeal,
    onPrimaryContainer = ExplorerNavy,
    secondary = ExplorerTeal,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDFF0EE),
    onSecondaryContainer = ExplorerNavy,
    tertiary = ExplorerCoral,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFECE5),
    onTertiaryContainer = Color(0xFF5D2416),
    background = ExplorerIvory,
    onBackground = ExplorerInk,
    surface = Color.White,
    onSurface = ExplorerInk,
    surfaceVariant = ExplorerCardBgSecondary,
    onSurfaceVariant = ExplorerMuted,
    outline = ExplorerBorder,
    outlineVariant = Color(0xFFD0DFDC)
)

private val DarkColorScheme = darkColorScheme(
    primary = ExplorerTeal,
    onPrimary = ExplorerNavyDark,
    primaryContainer = ExplorerCardDark,
    onPrimaryContainer = ExplorerIvoryDark,
    secondary = ExplorerGold,
    onSecondary = ExplorerNavyDark,
    secondaryContainer = ExplorerCardDark,
    onSecondaryContainer = ExplorerGold,
    tertiary = ExplorerCoral,
    onTertiary = ExplorerNavyDark,
    background = ExplorerNavyDark,
    onBackground = ExplorerIvoryDark,
    surface = ExplorerSurfaceDark,
    onSurface = ExplorerIvoryDark,
    surfaceVariant = ExplorerCardDark,
    onSurfaceVariant = ExplorerMutedDark,
    outline = ExplorerBorderDark,
    outlineVariant = Color(0xFF224350)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
