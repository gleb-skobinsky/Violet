package org.violet.uiKit.ripple.node

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.node.DelegatableNode

fun interface ColoredRippleNodeProducer<Node : DelegatableNode> {
    fun create(
        color: ColorProducer,
        alpha: () -> RippleAlpha
    ): Node
}

fun interface AlphaRippleNodeProducer<Node: DelegatableNode> {
    fun create(
        alpha: () -> RippleAlpha
    ): Node
}