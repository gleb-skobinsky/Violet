package org.violet.uiKit.ripple.node.opacity

import androidx.compose.animation.core.Animatable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.unit.Constraints

class OpacityRippleNode :
    DelegatableNode,
    Modifier.Node(),
    LayoutModifierNode {
    val animatedAlpha = Animatable(1f)
    override val shouldAutoInvalidate: Boolean = false

    private val layerBlock: GraphicsLayerScope.() -> Unit = {
        alpha = animatedAlpha.value
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            placeable.placeWithLayer(0, 0, layerBlock = layerBlock)
        }
    }
}