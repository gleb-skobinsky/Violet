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
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.violet.uiKit.ripple.node.experimental.BetaRippleApi
import org.violet.uiKit.ripple.node.opacity.opacityRipple
import org.violet.uiKit.ripple.node.rememberCustomRipple
import org.violet.uiKit.ripple.node.universalRipple
import org.violet.uiKit.ripple.utils.IcHeartEmpty
import org.violet.uiKit.ripple.utils.inferHeightForWidth

@Composable
fun UniversalButton() {
    CompositionLocalProvider(
        LocalIndication provides universalRipple()
    ) {
        ClickableBox()
    }
}

@Composable
fun FadingButton() {
    CompositionLocalProvider(
        LocalIndication provides opacityRipple(300, 300)
    ) {
        ClickableBox()
    }
}

@Composable
fun MaterialButton() {
    CompositionLocalProvider(
        LocalIndication provides ripple()
    ) {
        ClickableBox()
    }
}

@OptIn(BetaRippleApi::class)
@Composable
fun HeartButton() {
    val painter = rememberVectorPainter(IcHeartEmpty)
    CompositionLocalProvider(
        LocalIndication provides rememberCustomRipple(
            color = Color.Red,
            radius = 160.dp
        ) { color, center, radius ->
            drawHeart(painter, radius, center, color)
        }
    ) {
        ClickableBox(DpSize(300.dp, 300.dp))
    }
}

private fun DrawScope.drawHeart(
    painter: VectorPainter,
    radius: Float,
    center: Offset,
    color: Color
) {
    with(painter) {
        val width = radius * 2
        val height = inferHeightForWidth(width)
        val left = center.x - radius
        val top = center.y - (height / 2)
        println("Center: ${center.x} ${center.y} w $width h $height")
        //println("Left: $left top: $top")
        inset(
            left = left,
            top = top,
            right = 0f,
            bottom = 0f
        ) {
            draw(
                size = Size(width, height),
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

@Composable
private fun ClickableBox(
    size: DpSize = DpSize(200.dp, 48.dp)
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {}
            .background(Color.LightGray)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Text("Button", fontSize = 24.sp)
    }
}

@Preview
@Composable
fun ExampleButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Cyan),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UniversalButton()
        MaterialButton()
        FadingButton()
        HeartButton()
    }
}

