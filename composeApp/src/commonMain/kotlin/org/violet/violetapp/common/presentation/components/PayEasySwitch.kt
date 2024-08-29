package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
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
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondaryContainer
            )
        }
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primaryContainer,
                checkedIconColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primary,

                uncheckedThumbColor = MaterialTheme.colorScheme.onTertiary,
                uncheckedIconColor = MaterialTheme.colorScheme.tertiary,
                uncheckedTrackColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}