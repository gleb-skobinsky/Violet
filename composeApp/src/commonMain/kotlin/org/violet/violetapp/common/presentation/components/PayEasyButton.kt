package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.violet.violetapp.common.icons.ArrowRight
import org.violet.violetapp.common.presentation.material.dangerGradient
import org.violet.violetapp.common.presentation.material.primaryGradient

enum class VioletAppButtonType {
    Primary,
    Danger
}

@Composable
private fun VioletAppButtonType.toColors(): ButtonColors = when (this) {
    VioletAppButtonType.Primary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.tertiary,
        disabledContentColor = MaterialTheme.colorScheme.onTertiary,
    )

    VioletAppButtonType.Danger -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        disabledContainerColor = MaterialTheme.colorScheme.tertiary,
        disabledContentColor = MaterialTheme.colorScheme.onTertiary,
    )
}

@Composable
fun VioletAppButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = ArrowRight,
    type: VioletAppButtonType = VioletAppButtonType.Primary,
    onClick: () -> Unit
) {
    val enabledInternal = enabled && !isLoading
    val colors = type.toColors()
    val textColor = if (enabled) colors.contentColor else colors.disabledContentColor
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(CircleShape)
            .colorOrGradient(type, enabled, colors)
            .clickable(onClick = onClick, enabled = enabledInternal)
            .padding(ButtonDefaults.ContentPadding),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            VioletAppTwinklingProgress(textColor)
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                leftIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Button left icon",
                        modifier = Modifier.size(24.dp),
                        tint = textColor
                    )
                    8.dp.HorizontalSpacer()
                }
                Text(
                    text = label.uppercase(),
                    color = textColor,
                    fontSize = 16.sp
                )
                rightIcon?.let {
                    8.dp.HorizontalSpacer()
                    Icon(
                        imageVector = it,
                        contentDescription = "Button right icon",
                        modifier = Modifier.size(24.dp),
                        tint = textColor
                    )
                }
            }
        }
    }
}

private fun Modifier.colorOrGradient(
    type: VioletAppButtonType,
    enabled: Boolean,
    colors: ButtonColors
) = when {
    type == VioletAppButtonType.Primary && enabled -> this.primaryGradient()
    type == VioletAppButtonType.Danger && enabled -> this.dangerGradient()
    enabled -> this.background(colors.containerColor)
    else -> this.background(colors.disabledContainerColor)
}