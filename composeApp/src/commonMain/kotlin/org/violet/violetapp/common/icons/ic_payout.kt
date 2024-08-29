package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Payout: ImageVector
    get() {
        if (_Payout != null) {
            return _Payout!!
        }
        _Payout = ImageVector.Builder(
            name = "Payout",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.333f, 15.667f)
                curveTo(17.079f, 15.667f, 16.826f, 15.573f, 16.626f, 15.373f)
                curveTo(16.239f, 14.987f, 16.239f, 14.347f, 16.626f, 13.96f)
                lineTo(27.559f, 3.027f)
                curveTo(27.946f, 2.64f, 28.586f, 2.64f, 28.973f, 3.027f)
                curveTo(29.359f, 3.413f, 29.359f, 4.053f, 28.973f, 4.44f)
                lineTo(18.039f, 15.373f)
                curveTo(17.839f, 15.573f, 17.586f, 15.667f, 17.333f, 15.667f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(29.334f, 10.066f)
                curveTo(28.787f, 10.066f, 28.334f, 9.613f, 28.334f, 9.066f)
                verticalLineTo(3.667f)
                horizontalLineTo(22.934f)
                curveTo(22.387f, 3.667f, 21.934f, 3.213f, 21.934f, 2.667f)
                curveTo(21.934f, 2.12f, 22.387f, 1.666f, 22.934f, 1.666f)
                horizontalLineTo(29.334f)
                curveTo(29.88f, 1.666f, 30.334f, 2.12f, 30.334f, 2.667f)
                verticalLineTo(9.066f)
                curveTo(30.334f, 9.613f, 29.88f, 10.066f, 29.334f, 10.066f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(19.999f, 30.333f)
                horizontalLineTo(11.999f)
                curveTo(4.759f, 30.333f, 1.666f, 27.24f, 1.666f, 20f)
                verticalLineTo(12f)
                curveTo(1.666f, 4.76f, 4.759f, 1.666f, 11.999f, 1.666f)
                horizontalLineTo(14.666f)
                curveTo(15.213f, 1.666f, 15.666f, 2.12f, 15.666f, 2.667f)
                curveTo(15.666f, 3.213f, 15.213f, 3.667f, 14.666f, 3.667f)
                horizontalLineTo(11.999f)
                curveTo(5.853f, 3.667f, 3.666f, 5.853f, 3.666f, 12f)
                verticalLineTo(20f)
                curveTo(3.666f, 26.146f, 5.853f, 28.333f, 11.999f, 28.333f)
                horizontalLineTo(19.999f)
                curveTo(26.146f, 28.333f, 28.333f, 26.146f, 28.333f, 20f)
                verticalLineTo(17.333f)
                curveTo(28.333f, 16.787f, 28.786f, 16.333f, 29.333f, 16.333f)
                curveTo(29.879f, 16.333f, 30.333f, 16.787f, 30.333f, 17.333f)
                verticalLineTo(20f)
                curveTo(30.333f, 27.24f, 27.239f, 30.333f, 19.999f, 30.333f)
                close()
            }
        }.build()

        return _Payout!!
    }

@Suppress("ObjectPropertyName")
private var _Payout: ImageVector? = null
