package org.violet.violetapp.common.presentation.material

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.violet.violetapp.common.presentation.modifiers.diagonalGradient

val VioletAppColorScheme = ColorScheme(
    primary = Color(244, 128, 102),
    onPrimary = Color(48, 48, 48),
    primaryContainer = Color(255, 255, 255),
    onPrimaryContainer = Color(20, 14, 23),
    inversePrimary = Color.Unspecified,
    secondary = Color(231, 231, 231),
    onSecondary = Color(170, 170, 170),
    secondaryContainer = Color(170, 170, 170),
    onSecondaryContainer = Color(20, 14, 23),
    tertiary = Color(64, 64, 64),
    onTertiary = Color(119, 117, 127),
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    background = Color.Unspecified,
    onBackground = Color.Unspecified,
    surface = Color(48, 48, 48),
    onSurface = Color.Unspecified,
    surfaceVariant = Color(64, 64, 64),
    onSurfaceVariant = Color(244, 128, 102),
    surfaceTint = Color(0x80606060),
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    error = Color(255, 60, 99),
    onError = Color.White,
    errorContainer = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    outline = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    scrim = Color.Unspecified,
    surfaceBright = Color.Unspecified,
    surfaceDim = Color.Unspecified,
    surfaceContainer = Color.Unspecified,
    surfaceContainerHigh = Color(64f, 64f, 64f, 0.8f),
    surfaceContainerHighest = Color.Unspecified,
    surfaceContainerLow = Color.Unspecified,
    surfaceContainerLowest = Color.Unspecified
)

private val primaryGradientColors = listOf(
    Color(58, 221, 94),
    Color(115, 241, 120),
    Color(93, 235, 133)
)

private val dangerColors = listOf(
    Color(255, 162, 110),
    Color(255, 60, 99)
)

val PrimaryGradient: Brush = Brush.linearGradient(
    colors = primaryGradientColors,
    start = Offset.Zero,
    end = Offset.Infinite
)

fun Modifier.primaryGradient() = this.diagonalGradient(primaryGradientColors)

fun Modifier.dangerGradient() = this.diagonalGradient(dangerColors)