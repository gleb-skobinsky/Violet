package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AddSquare: ImageVector
    get() {
        if (_AddSquare != null) {
            return _AddSquare!!
        }
        _AddSquare = ImageVector.Builder(
            name = "AddSquare",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(16.5f, 12.75f)
                horizontalLineTo(8.5f)
                curveTo(8.09f, 12.75f, 7.75f, 12.41f, 7.75f, 12f)
                curveTo(7.75f, 11.59f, 8.09f, 11.25f, 8.5f, 11.25f)
                horizontalLineTo(16.5f)
                curveTo(16.91f, 11.25f, 17.25f, 11.59f, 17.25f, 12f)
                curveTo(17.25f, 12.41f, 16.91f, 12.75f, 16.5f, 12.75f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(12.5f, 16.75f)
                curveTo(12.09f, 16.75f, 11.75f, 16.41f, 11.75f, 16f)
                verticalLineTo(8f)
                curveTo(11.75f, 7.59f, 12.09f, 7.25f, 12.5f, 7.25f)
                curveTo(12.91f, 7.25f, 13.25f, 7.59f, 13.25f, 8f)
                verticalLineTo(16f)
                curveTo(13.25f, 16.41f, 12.91f, 16.75f, 12.5f, 16.75f)
                close()
            }
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
        }.build()

        return _AddSquare!!
    }

@Suppress("ObjectPropertyName")
private var _AddSquare: ImageVector? = null
