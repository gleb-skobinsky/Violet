package org.violet.uiKit.ripple.node

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
internal class DelegatingRippleNode(
    private val interactionSource: InteractionSource,
    private val bounded: Boolean,
    private val radius: Dp,
    private val color: Color,
) : DelegatingNode(), CompositionLocalConsumerModifierNode,
    ObserverModifierNode {
    private var rippleNode: DelegatableNode? = null

    override fun onAttach() {
        updateConfiguration()
    }

    override fun onObservedReadsChanged() {
        updateConfiguration()
    }

    /**
     * Handles [LocalRippleConfiguration] changing between null / non-null. Changes to
     * [RippleConfiguration.color] and [RippleConfiguration.rippleAlpha] are handled as part of the
     * ripple definition.
     */
    private fun updateConfiguration() {
        observeReads {
            val configuration = currentValueOf(LocalRippleConfiguration)
            if (configuration == null) {
                removeRipple()
            } else {
                if (rippleNode == null) attachNewRipple()
            }
        }
    }

    private fun attachNewRipple() {
        val calculateColor = ColorProducer {
            if (color.isSpecified) {
                color
            } else {
                // If this is null, the ripple will be removed, so this should always be non-null in
                // normal use
                val rippleConfiguration =
                    currentValueOf(LocalRippleConfiguration)
                if (rippleConfiguration?.color?.isSpecified == true) {
                    rippleConfiguration.color
                } else {
                    currentValueOf(LocalContentColor)
                }
            }
        }

        val calculateRippleAlpha = {
            // If this is null, the ripple will be removed, so this should always be non-null in
            // normal use
            val rippleConfiguration = currentValueOf(LocalRippleConfiguration)
            rippleConfiguration?.rippleAlpha ?: RippleDefaults.RippleAlpha
        }

        rippleNode =
            delegate(
                createUniversalRippleNode(
                    interactionSource,
                    bounded,
                    radius,
                    calculateColor,
                    calculateRippleAlpha
                )
            )
    }

    private fun removeRipple() {
        rippleNode?.let { undelegate(it) }
    }
}
