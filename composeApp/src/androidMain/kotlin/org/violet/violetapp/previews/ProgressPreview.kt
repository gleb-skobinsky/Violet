package org.violet.violetapp.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.violet.violetapp.common.presentation.components.VioletAppTwinklingProgress

@Composable
@Preview
fun ProgressPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black), Alignment.Center
    ) {
        VioletAppTwinklingProgress(Color.White, 200.dp)
    }
}