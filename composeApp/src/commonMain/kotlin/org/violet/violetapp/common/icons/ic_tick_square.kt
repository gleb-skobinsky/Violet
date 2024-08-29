package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TickSquare: ImageVector
    get() {
        if (_TickSquare != null) {
            return _TickSquare!!
        }
        _TickSquare = ImageVector.Builder(
            name = "TickSquare",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(15.5f, 22.75f)
                horizontalLineTo(9.5f)
                curveTo(4.07f, 22.75f, 1.75f, 20.43f, 1.75f, 15f)
                verticalLineTo(9f)
                curveTo(1.75f, 3.57f, 4.07f, 1.25f, 9.5f, 1.25f)
                horizontalLineTo(15.5f)
                curveTo(20.93f, 1.25f, 23.25f, 3.57f, 23.25f, 9f)
                verticalLineTo(15f)
                curveTo(23.25f, 20.43f, 20.93f, 22.75f, 15.5f, 22.75f)
                close()
                moveTo(9.5f, 2.75f)
                curveTo(4.89f, 2.75f, 3.25f, 4.39f, 3.25f, 9f)
                verticalLineTo(15f)
                curveTo(3.25f, 19.61f, 4.89f, 21.25f, 9.5f, 21.25f)
                horizontalLineTo(15.5f)
                curveTo(20.11f, 21.25f, 21.75f, 19.61f, 21.75f, 15f)
                verticalLineTo(9f)
                curveTo(21.75f, 4.39f, 20.11f, 2.75f, 15.5f, 2.75f)
                horizontalLineTo(9.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(11.08f, 15.58f)
                curveTo(10.88f, 15.58f, 10.689f, 15.5f, 10.55f, 15.36f)
                lineTo(7.719f, 12.53f)
                curveTo(7.429f, 12.24f, 7.429f, 11.76f, 7.719f, 11.47f)
                curveTo(8.009f, 11.18f, 8.489f, 11.18f, 8.779f, 11.47f)
                lineTo(11.08f, 13.77f)
                lineTo(16.219f, 8.63f)
                curveTo(16.51f, 8.34f, 16.99f, 8.34f, 17.279f, 8.63f)
                curveTo(17.569f, 8.92f, 17.569f, 9.4f, 17.279f, 9.69f)
                lineTo(11.609f, 15.36f)
                curveTo(11.469f, 15.5f, 11.28f, 15.58f, 11.08f, 15.58f)
                close()
            }
        }.build()

        return _TickSquare!!
    }

@Suppress("ObjectPropertyName")
private var _TickSquare: ImageVector? = null
