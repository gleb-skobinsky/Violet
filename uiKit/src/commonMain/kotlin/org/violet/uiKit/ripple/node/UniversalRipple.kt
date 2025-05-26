package org.violet.uiKit.ripple.node

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Stable
fun universalRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): IndicationNodeFactory {
    return if (radius == Dp.Unspecified && color == Color.Unspecified) {
        if (bounded) return DefaultBoundedRipple else DefaultUnboundedRipple
    } else {
        RippleNodeFactory(bounded, radius, color)
    }
}

private val DefaultBoundedRipple = RippleNodeFactory(
    bounded = true,
    radius = Dp.Unspecified,
    color = Color.Unspecified
)
private val DefaultUnboundedRipple = RippleNodeFactory(
    bounded = false,
    radius = Dp.Unspecified,
    color = Color.Unspecified
)