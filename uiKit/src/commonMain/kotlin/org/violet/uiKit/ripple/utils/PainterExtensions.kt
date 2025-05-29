package org.violet.uiKit.ripple.utils

import androidx.compose.ui.graphics.painter.Painter

inline val Painter.painterDimRatio: Float
    get() = intrinsicSize.height / intrinsicSize.width

fun Painter.inferHeightForWidth(
    width: Float
): Float {
    return width * painterDimRatio
}

