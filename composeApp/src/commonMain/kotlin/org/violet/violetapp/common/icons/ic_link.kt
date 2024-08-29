package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Link: ImageVector
    get() {
        if (_Link != null) {
            return _Link!!
        }
        _Link = ImageVector.Builder(
            name = "Link",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(16.668f, 19.667f)
                horizontalLineTo(13.334f)
                curveTo(12.788f, 19.667f, 12.334f, 19.213f, 12.334f, 18.667f)
                curveTo(12.334f, 18.12f, 12.788f, 17.667f, 13.334f, 17.667f)
                horizontalLineTo(16.668f)
                curveTo(20.161f, 17.667f, 23.001f, 14.827f, 23.001f, 11.333f)
                curveTo(23.001f, 7.84f, 20.161f, 5f, 16.668f, 5f)
                horizontalLineTo(10.001f)
                curveTo(6.508f, 5f, 3.668f, 7.84f, 3.668f, 11.333f)
                curveTo(3.668f, 12.8f, 4.188f, 14.227f, 5.121f, 15.36f)
                curveTo(5.468f, 15.787f, 5.414f, 16.413f, 4.988f, 16.773f)
                curveTo(4.561f, 17.12f, 3.934f, 17.067f, 3.574f, 16.64f)
                curveTo(2.334f, 15.147f, 1.654f, 13.267f, 1.654f, 11.333f)
                curveTo(1.654f, 6.733f, 5.388f, 3f, 9.988f, 3f)
                horizontalLineTo(16.654f)
                curveTo(21.254f, 3f, 24.988f, 6.733f, 24.988f, 11.333f)
                curveTo(24.988f, 15.933f, 21.268f, 19.667f, 16.668f, 19.667f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(22f, 29f)
                horizontalLineTo(15.333f)
                curveTo(10.733f, 29f, 7f, 25.267f, 7f, 20.667f)
                curveTo(7f, 16.067f, 10.733f, 12.333f, 15.333f, 12.333f)
                horizontalLineTo(18.667f)
                curveTo(19.213f, 12.333f, 19.667f, 12.787f, 19.667f, 13.333f)
                curveTo(19.667f, 13.88f, 19.213f, 14.333f, 18.667f, 14.333f)
                horizontalLineTo(15.333f)
                curveTo(11.84f, 14.333f, 9f, 17.174f, 9f, 20.667f)
                curveTo(9f, 24.16f, 11.84f, 27f, 15.333f, 27f)
                horizontalLineTo(22f)
                curveTo(25.493f, 27f, 28.333f, 24.16f, 28.333f, 20.667f)
                curveTo(28.333f, 19.2f, 27.813f, 17.774f, 26.88f, 16.64f)
                curveTo(26.533f, 16.213f, 26.587f, 15.587f, 27.013f, 15.227f)
                curveTo(27.44f, 14.867f, 28.067f, 14.934f, 28.427f, 15.36f)
                curveTo(29.667f, 16.854f, 30.347f, 18.733f, 30.347f, 20.667f)
                curveTo(30.333f, 25.267f, 26.6f, 29f, 22f, 29f)
                close()
            }
        }.build()

        return _Link!!
    }

@Suppress("ObjectPropertyName")
private var _Link: ImageVector? = null
