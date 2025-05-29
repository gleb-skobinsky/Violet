package org.violet.uiKit.ripple.node

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.unit.Dp

@Stable
fun universalRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): IndicationNodeFactory {
    return when {
        radius != Dp.Unspecified || color != Color.Unspecified -> {
            RippleNodeFactory(bounded, radius, color)
        }

        bounded -> DefaultBoundedRipple
        else -> DefaultUnboundedRipple
    }
}

@Stable
fun universalRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: ColorProducer
): IndicationNodeFactory {
    return RippleNodeFactory(bounded, radius, colorProducer = color)
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