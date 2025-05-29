package org.violet.uiKit.ripple.node

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.unit.Dp

@Stable
internal class RippleNodeFactory(
    private val bounded: Boolean,
    private val radius: Dp,
    private val color: ColorProducer
) : IndicationNodeFactory {

    constructor(bounded: Boolean, radius: Dp, color: Color) : this(
        bounded,
        radius,
        ColorProducer { color }
    )

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return DelegatingRippleNode(interactionSource, bounded, radius, color)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RippleNodeFactory) return false

        if (bounded != other.bounded) return false
        if (radius != other.radius) return false
        return color == other.color
    }

    override fun hashCode(): Int {
        var result = bounded.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }
}
