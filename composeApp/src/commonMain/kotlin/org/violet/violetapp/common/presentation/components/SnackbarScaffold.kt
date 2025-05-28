package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SnackbarScaffold(
    snackbarState: SnackbarHostState,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalVioletTheme.colors.surface),
        snackbarHost = {
            VioletAppSnackbar(snackbarState)
        },
        bottomBar = bottomBar,
        content = content
    )
}