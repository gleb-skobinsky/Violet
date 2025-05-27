package org.violet.uiKit

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.violet.uiKit.ripple.node.universalRipple

@Composable
fun UniversalButton() {
    CompositionLocalProvider(
        LocalIndication provides universalRipple()
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
                .size(200.dp)
                .clickable {}
        )
    }
}

@Composable
fun MaterialButton() {
    CompositionLocalProvider(
        LocalIndication provides ripple()
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
                .size(200.dp)
                .clickable {}
        )
    }
}

@Preview
@Composable
fun ExampleButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UniversalButton()
        MaterialButton()
    }
}

