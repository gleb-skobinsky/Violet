package org.violet.violetapp.common.presentation.modifiers

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

actual fun Modifier.tintHazeChild(
    hazeState: HazeState,
    color: Color
): Modifier = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
    this.hazeChild(state = hazeState, style = HazeDefaults.style(backgroundColor = color))
} else {
    this.background(color = color.copy(alpha = 1f))
}