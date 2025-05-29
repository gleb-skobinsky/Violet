package org.violet.uiKit.ripple.node

import androidx.compose.material3.RippleDefaults
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.observeReads

internal open class NullBasedRippleDelegator<Node : DelegatableNode>(
    private val delegatable: () -> Node
) : DelegatingNode() {
    private var rippleNode: Node? = null

    override fun onAttach() {
        if (rippleNode == null) {
            rippleNode = delegate(delegatable())
        }
    }

    override fun onDetach() {
        rippleNode?.let { undelegate(it) }
    }
}

internal open class DrawingAgnosticRippleNode<Node : DelegatableNode>(
    private val nodeProducer: AlphaRippleNodeProducer<Node>
) : DelegatingNode(), CompositionLocalConsumerModifierNode,
    ObserverModifierNode {
    private var rippleNode: Node? = null

    override fun onAttach() {
        updateConfiguration()
    }

    override fun onObservedReadsChanged() {
        updateConfiguration()
    }

    private fun updateConfiguration() {
        observeReads {
            val configuration = currentValueOf(LocalRippleConfig)
            if (configuration == null) {
                removeRipple()
            } else {
                if (rippleNode == null) {
                    attachNewRipple()
                }
            }
        }
    }

    private fun attachNewRipple() {

        val calculateRippleAlpha = {
            // If this is null, the ripple will be removed, so this should always be non-null in
            // normal use
            val rippleConfiguration = currentValueOf(LocalRippleConfig)
            rippleConfiguration?.rippleAlpha ?: RippleDefaults.RippleAlpha
        }

        rippleNode = delegate(
            nodeProducer.create(
                calculateRippleAlpha
            )
        )
    }

    private fun removeRipple() {
        rippleNode?.let { undelegate(it) }
    }
}
