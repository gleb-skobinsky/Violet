package org.violet.uiKit.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import org.violet.uiKit.ripple.node.opacity.opacityRipple
import org.violet.uiKit.ripple.node.universalRipple

@Stable
object LocalVioletTheme {
    val colors: VioletColors
        @Composable @ReadOnlyComposable get() = LocalVioletColors.current

    val typography: Typography
        @Composable @ReadOnlyComposable get() = LocalVioletTypography.current
}

val LocalVioletColors = staticCompositionLocalOf { VioletAppColorScheme }
val LocalVioletTypography = staticCompositionLocalOf<Typography> {
    error("No typography provided")
}


private const val TextSelectionBackgroundOpacity = 0.4f

@Composable
fun rememberTextSelectionColors(colorScheme: VioletColors): TextSelectionColors {
    val primaryColor = colorScheme.primary
    return remember(primaryColor) {
        TextSelectionColors(
            handleColor = primaryColor,
            backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity),
        )
    }
}


@Composable
fun VioletTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = VioletAppColorScheme
    CompositionLocalProvider(
        LocalVioletTypography provides violetAppTypography,
        LocalVioletColors provides VioletAppColorScheme,
        LocalIndication provides opacityRipple(300, 300),
        LocalTextSelectionColors provides rememberTextSelectionColors(
            colorScheme
        ),
        content = content
    )
}