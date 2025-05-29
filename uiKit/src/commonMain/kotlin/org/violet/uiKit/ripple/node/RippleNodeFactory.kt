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
    private val color: Color = Color.Unspecified,
    private val colorProducer: ColorProducer? = null,
    private val drawCommand: RippleDrawCommand = SmoothRippleCommand
) : IndicationNodeFactory {

    constructor(bounded: Boolean, radius: Dp, color: Color) : this(
        bounded = bounded,
        radius = radius,
        colorProducer = ColorProducer { color }
    )

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        val userDefinedColorProducer = colorProducer ?: ColorProducer { color }
        return DelegatingRippleNode(
            interactionSource = interactionSource,
            bounded = bounded,
            radius = radius,
            color = userDefinedColorProducer,
            drawCommand = drawCommand
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RippleNodeFactory) return false
        if (bounded != other.bounded) return false
        if (radius != other.radius) return false
        if (colorProducer != other.colorProducer) return false
        if (drawCommand != other.drawCommand) return false
        return color == other.color
    }

    override fun hashCode(): Int {
        var result = bounded.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + colorProducer.hashCode()
        result = 31 * result + drawCommand.hashCode()
        return result
    }
}
