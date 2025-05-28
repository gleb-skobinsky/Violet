package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.layout.Column
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun VioletAppSwitch(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    label: String = "",
    enabled: Boolean = true
) {
    Column {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = LocalVioletTheme.typography.bodyMedium,
                color = LocalVioletTheme.colors.secondaryContainer
            )
        }
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = LocalVioletTheme.colors.primaryContainer,
                checkedIconColor = LocalVioletTheme.colors.primary,
                checkedTrackColor = LocalVioletTheme.colors.primary,

                uncheckedThumbColor = LocalVioletTheme.colors.onTertiary,
                uncheckedIconColor = LocalVioletTheme.colors.tertiary,
                uncheckedTrackColor = LocalVioletTheme.colors.tertiary
            )
        )
    }
}