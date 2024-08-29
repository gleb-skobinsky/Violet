package org.violet.violetapp.common.presentation.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

actual fun Modifier.tintHazeChild(
    hazeState: HazeState,
    color: Color
) = this.hazeChild(
    state = hazeState,
    style = HazeDefaults.style(backgroundColor = color)
)