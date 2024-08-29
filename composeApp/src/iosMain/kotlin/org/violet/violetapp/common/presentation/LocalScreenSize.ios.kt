package org.violet.violetapp.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.toSize

actual object LocalScreenSize {

    actual val current: DpSize
        @OptIn(ExperimentalComposeUiApi::class)
        @Composable
        get() = with(LocalDensity.current) {
            val pxSize = LocalWindowInfo.current.containerSize.toSize()
            DpSize(pxSize.width.toDp(), pxSize.height.toDp())
        }
}