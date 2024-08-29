package org.violet.violetapp.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

actual object LocalScreenSize {
    actual val current: DpSize
        @Composable
        get() = with(LocalConfiguration.current) {
            return DpSize(screenWidthDp.dp, screenHeightDp.dp)
        }
}