package org.violet.violetapp.common.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val LockCircle: ImageVector
    get() {
        if (_LockCircle != null) {
            return _LockCircle!!
        }
        _LockCircle = ImageVector.Builder(
            name = "LockCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF3ADD5E),
                        0.5f to Color(0xFF73F178),
                        1f to Color(0xFF5DEB85)
                    ),
                    start = Offset(8.25f, 6.25f),
                    end = Offset(12.563f, 13.094f)
                )
            ) {
                moveTo(15f, 11.75f)
                curveTo(14.59f, 11.75f, 14.25f, 11.41f, 14.25f, 11f)
                verticalLineTo(10f)
                curveTo(14.25f, 8.54f, 13.89f, 7.75f, 12f, 7.75f)
                curveTo(10.11f, 7.75f, 9.75f, 8.54f, 9.75f, 10f)
                verticalLineTo(11f)
                curveTo(9.75f, 11.41f, 9.41f, 11.75f, 9f, 11.75f)
                curveTo(8.59f, 11.75f, 8.25f, 11.41f, 8.25f, 11f)
                verticalLineTo(10f)
                curveTo(8.25f, 9.07f, 8.25f, 6.25f, 12f, 6.25f)
                curveTo(15.75f, 6.25f, 15.75f, 9.07f, 15.75f, 10f)
                verticalLineTo(11f)
                curveTo(15.75f, 11.41f, 15.41f, 11.75f, 15f, 11.75f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF3ADD5E),
                        0.5f to Color(0xFF73F178),
                        1f to Color(0xFF5DEB85)
                    ),
                    start = Offset(10.65f, 12.65f),
                    end = Offset(12.971f, 15.35f)
                )
            ) {
                moveTo(12f, 15.35f)
                curveTo(11.26f, 15.35f, 10.65f, 14.74f, 10.65f, 14f)
                curveTo(10.65f, 13.26f, 11.26f, 12.65f, 12f, 12.65f)
                curveTo(12.74f, 12.65f, 13.35f, 13.26f, 13.35f, 14f)
                curveTo(13.35f, 14.74f, 12.74f, 15.35f, 12f, 15.35f)
                close()
                moveTo(12f, 13.85f)
                curveTo(11.92f, 13.85f, 11.85f, 13.92f, 11.85f, 14f)
                curveTo(11.85f, 14.17f, 12.15f, 14.17f, 12.15f, 14f)
                curveTo(12.15f, 13.92f, 12.08f, 13.85f, 12f, 13.85f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF3ADD5E),
                        0.5f to Color(0xFF73F178),
                        1f to Color(0xFF5DEB85)
                    ),
                    start = Offset(6.25f, 10.25f),
                    end = Offset(11.811f, 20.172f)
                )
            ) {
                moveTo(14.5f, 17.75f)
                horizontalLineTo(9.5f)
                curveTo(7.1f, 17.75f, 6.25f, 16.9f, 6.25f, 14.5f)
                verticalLineTo(13.5f)
                curveTo(6.25f, 11.1f, 7.1f, 10.25f, 9.5f, 10.25f)
                horizontalLineTo(14.5f)
                curveTo(16.9f, 10.25f, 17.75f, 11.1f, 17.75f, 13.5f)
                verticalLineTo(14.5f)
                curveTo(17.75f, 16.9f, 16.9f, 17.75f, 14.5f, 17.75f)
                close()
                moveTo(9.5f, 11.75f)
                curveTo(7.91f, 11.75f, 7.75f, 11.91f, 7.75f, 13.5f)
                verticalLineTo(14.5f)
                curveTo(7.75f, 16.09f, 7.91f, 16.25f, 9.5f, 16.25f)
                horizontalLineTo(14.5f)
                curveTo(16.09f, 16.25f, 16.25f, 16.09f, 16.25f, 14.5f)
                verticalLineTo(13.5f)
                curveTo(16.25f, 11.91f, 16.09f, 11.75f, 14.5f, 11.75f)
                horizontalLineTo(9.5f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF3ADD5E),
                        0.5f to Color(0xFF73F178),
                        1f to Color(0xFF5DEB85)
                    ),
                    start = Offset(1.25f, 1.25f),
                    end = Offset(19.727f, 22.75f)
                )
            ) {
                moveTo(12f, 22.75f)
                curveTo(6.07f, 22.75f, 1.25f, 17.93f, 1.25f, 12f)
                curveTo(1.25f, 6.07f, 6.07f, 1.25f, 12f, 1.25f)
                curveTo(17.93f, 1.25f, 22.75f, 6.07f, 22.75f, 12f)
                curveTo(22.75f, 17.93f, 17.93f, 22.75f, 12f, 22.75f)
                close()
                moveTo(12f, 2.75f)
                curveTo(6.9f, 2.75f, 2.75f, 6.9f, 2.75f, 12f)
                curveTo(2.75f, 17.1f, 6.9f, 21.25f, 12f, 21.25f)
                curveTo(17.1f, 21.25f, 21.25f, 17.1f, 21.25f, 12f)
                curveTo(21.25f, 6.9f, 17.1f, 2.75f, 12f, 2.75f)
                close()
            }
        }.build()

        return _LockCircle!!
    }

@Suppress("ObjectPropertyName")
private var _LockCircle: ImageVector? = null
