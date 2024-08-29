package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Withdrawal: ImageVector
    get() {
        if (_Withdrawal != null) {
            return _Withdrawal!!
        }
        _Withdrawal = ImageVector.Builder(
            name = "Withdrawal",
            defaultWidth = 16.dp,
            defaultHeight = 17.dp,
            viewportWidth = 16f,
            viewportHeight = 17f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
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
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(7.571f, 10.011f)
                curveTo(7.892f, 10.011f, 8.153f, 9.746f, 8.153f, 9.419f)
                verticalLineTo(4.923f)
                lineTo(9.487f, 6.282f)
                curveTo(9.714f, 6.514f, 10.083f, 6.514f, 10.31f, 6.282f)
                curveTo(10.537f, 6.051f, 10.537f, 5.675f, 10.31f, 5.444f)
                lineTo(7.983f, 3.074f)
                curveTo(7.755f, 2.842f, 7.387f, 2.842f, 7.16f, 3.074f)
                lineTo(4.833f, 5.444f)
                curveTo(4.605f, 5.675f, 4.605f, 6.051f, 4.833f, 6.282f)
                curveTo(5.06f, 6.514f, 5.428f, 6.514f, 5.655f, 6.282f)
                lineTo(6.989f, 4.923f)
                verticalLineTo(9.419f)
                curveTo(6.989f, 9.746f, 7.25f, 10.011f, 7.571f, 10.011f)
                close()
            }
        }.build()

        return _Withdrawal!!
    }

@Suppress("ObjectPropertyName")
private var _Withdrawal: ImageVector? = null
