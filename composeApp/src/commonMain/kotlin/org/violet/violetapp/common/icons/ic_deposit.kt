package org.violet.violetapp.common.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Deposit: ImageVector
    get() {
        if (_Deposit != null) {
            return _Deposit!!
        }
        _Deposit = ImageVector.Builder(
            name = "Deposit",
            defaultWidth = 16.dp,
            defaultHeight = 17.dp,
            viewportWidth = 16f,
            viewportHeight = 17f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.017f to Color(0xFF7ADE2B),
                        1f to Color(0xFF15B91B)
                    ),
                    start = Offset(2.4f, 12.273f),
                    end = Offset(2.931f, 14.643f)
                ),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(2.4f, 12.92f)
                curveTo(2.4f, 12.563f, 2.666f, 12.273f, 2.993f, 12.273f)
                horizontalLineTo(12.474f)
                curveTo(12.802f, 12.273f, 13.067f, 12.563f, 13.067f, 12.92f)
                curveTo(13.067f, 13.277f, 12.802f, 13.566f, 12.474f, 13.566f)
                horizontalLineTo(2.993f)
                curveTo(2.666f, 13.566f, 2.4f, 13.277f, 2.4f, 12.92f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.017f to Color(0xFF7ADE2B),
                        1f to Color(0xFF15B91B)
                    ),
                    start = Offset(4.662f, 2.9f),
                    end = Offset(9.726f, 5.141f)
                ),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(7.571f, 2.9f)
                curveTo(7.892f, 2.9f, 8.153f, 3.165f, 8.153f, 3.492f)
                verticalLineTo(7.988f)
                lineTo(9.487f, 6.629f)
                curveTo(9.714f, 6.398f, 10.083f, 6.398f, 10.31f, 6.629f)
                curveTo(10.537f, 6.86f, 10.537f, 7.236f, 10.31f, 7.467f)
                lineTo(7.983f, 9.837f)
                curveTo(7.755f, 10.069f, 7.387f, 10.069f, 7.16f, 9.837f)
                lineTo(4.833f, 7.467f)
                curveTo(4.605f, 7.236f, 4.605f, 6.86f, 4.833f, 6.629f)
                curveTo(5.06f, 6.398f, 5.428f, 6.398f, 5.655f, 6.629f)
                lineTo(6.989f, 7.988f)
                verticalLineTo(3.492f)
                curveTo(6.989f, 3.165f, 7.25f, 2.9f, 7.571f, 2.9f)
                close()
            }
        }.build()

        return _Deposit!!
    }

@Suppress("ObjectPropertyName")
private var _Deposit: ImageVector? = null
