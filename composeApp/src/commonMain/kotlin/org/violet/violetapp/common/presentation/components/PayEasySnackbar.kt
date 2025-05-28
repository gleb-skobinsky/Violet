package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun VioletAppSnackbar(snackbarState: SnackbarHostState) {
    SnackbarHost(snackbarState) { data ->
        Snackbar(
            snackbarData = data,
            shape = RoundedCornerShape(12.dp),
            containerColor = LocalVioletTheme.colors.secondary,
            contentColor = LocalVioletTheme.colors.onSecondaryContainer,
        )
    }
}