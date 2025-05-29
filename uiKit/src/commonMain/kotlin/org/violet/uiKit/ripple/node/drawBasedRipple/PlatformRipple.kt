package org.violet.uiKit.ripple.node.drawBasedRipple

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.unit.Dp

fun createUniversalRippleNode(
    interactionSource: InteractionSource,
    bounded: Boolean,
    radius: Dp,
    color: ColorProducer,
    rippleAlpha: () -> RippleAlpha,
    drawCommand: RippleDrawCommand
): DelegatableNode {
    return CommonRippleNode(
        interactionSource = interactionSource,
        bounded = bounded,
        radius = radius,
        color = color,
        rippleAlpha = rippleAlpha,
        drawCommand = drawCommand
    )
}