package org.violet.uiKit.ripple.node.opacity

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.node.DelegatableNode
import org.violet.uiKit.ripple.node.NullBasedRippleDelegator

@Stable
internal class OpacityRippleFactory(
    private val fadeInSpec: FiniteAnimationSpec<Float>,
    private val fadeOutSpec: FiniteAnimationSpec<Float>,
    private val minAlpha: Float,
) : IndicationNodeFactory {
    constructor(
        fadeInDuration: Int,
        fadeOutDuration: Int,
        minAlpha: Float,
    ) : this(
        tween(fadeInDuration),
        tween(fadeOutDuration),
        minAlpha
    )

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NullBasedRippleDelegator { OpacityRippleNode() }
    }

    override fun hashCode(): Int {
        var result = fadeInSpec.hashCode()
        result = 31 * result + fadeOutSpec.hashCode()
        result = 31 * result + minAlpha.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OpacityRippleFactory) return false
        if (fadeInSpec != other.fadeInSpec) return false
        if (minAlpha != other.minAlpha) return false
        return fadeOutSpec == other.fadeOutSpec
    }

}