package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Strongbox: ImageVector
    get() {
        if (_Strongbox != null) {
            return _Strongbox!!
        }
        _Strongbox = ImageVector.Builder(
            name = "Strongbox",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = CommonIconColor) {
                moveTo(15f, 22.75f)
                horizontalLineTo(9f)
                curveTo(3.57f, 22.75f, 1.25f, 20.43f, 1.25f, 15f)
                verticalLineTo(9f)
                curveTo(1.25f, 3.57f, 3.57f, 1.25f, 9f, 1.25f)
                horizontalLineTo(15f)
                curveTo(20.43f, 1.25f, 22.75f, 3.57f, 22.75f, 9f)
                verticalLineTo(15f)
                curveTo(22.75f, 20.43f, 20.43f, 22.75f, 15f, 22.75f)
                close()
                moveTo(9f, 2.75f)
                curveTo(4.39f, 2.75f, 2.75f, 4.39f, 2.75f, 9f)
                verticalLineTo(15f)
                curveTo(2.75f, 19.61f, 4.39f, 21.25f, 9f, 21.25f)
                horizontalLineTo(15f)
                curveTo(19.61f, 21.25f, 21.25f, 19.61f, 21.25f, 15f)
                verticalLineTo(9f)
                curveTo(21.25f, 4.39f, 19.61f, 2.75f, 15f, 2.75f)
                horizontalLineTo(9f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(12f, 16.56f)
                curveTo(10.9f, 16.56f, 10f, 15.66f, 10f, 14.56f)
                verticalLineTo(12.96f)
                curveTo(9.41f, 12.47f, 9.01f, 11.78f, 8.91f, 11.01f)
                curveTo(8.89f, 10.89f, 8.87f, 10.72f, 8.87f, 10.56f)
                curveTo(8.87f, 9.59f, 9.31f, 8.69f, 10.09f, 8.09f)
                curveTo(10.87f, 7.49f, 11.87f, 7.3f, 12.83f, 7.55f)
                curveTo(13.88f, 7.83f, 14.75f, 8.69f, 15.02f, 9.74f)
                curveTo(15.13f, 10.15f, 15.16f, 10.58f, 15.09f, 11.01f)
                curveTo(14.98f, 11.77f, 14.59f, 12.46f, 14f, 12.95f)
                verticalLineTo(14.55f)
                curveTo(14f, 15.66f, 13.1f, 16.56f, 12f, 16.56f)
                close()
                moveTo(11.99f, 8.94f)
                curveTo(11.63f, 8.94f, 11.29f, 9.05f, 11f, 9.28f)
                curveTo(10.6f, 9.59f, 10.37f, 10.06f, 10.37f, 10.56f)
                curveTo(10.37f, 10.63f, 10.37f, 10.71f, 10.39f, 10.77f)
                curveTo(10.46f, 11.27f, 10.73f, 11.68f, 11.15f, 11.94f)
                lineTo(11.5f, 12.16f)
                verticalLineTo(14.56f)
                curveTo(11.5f, 14.84f, 11.72f, 15.06f, 12f, 15.06f)
                curveTo(12.28f, 15.06f, 12.5f, 14.84f, 12.5f, 14.56f)
                verticalLineTo(12.16f)
                lineTo(12.85f, 11.94f)
                curveTo(13.26f, 11.68f, 13.54f, 11.27f, 13.6f, 10.8f)
                verticalLineTo(10.78f)
                curveTo(13.64f, 10.56f, 13.63f, 10.34f, 13.57f, 10.13f)
                curveTo(13.43f, 9.58f, 12.98f, 9.14f, 12.44f, 9f)
                curveTo(12.29f, 8.96f, 12.14f, 8.94f, 11.99f, 8.94f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(22f, 10.16f)
                horizontalLineTo(14.35f)
                verticalLineTo(11.66f)
                horizontalLineTo(22f)
                verticalLineTo(10.16f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(9.65f, 10.16f)
                horizontalLineTo(2f)
                verticalLineTo(11.66f)
                horizontalLineTo(9.65f)
                verticalLineTo(10.16f)
                close()
            }
        }.build()

        return _Strongbox!!
    }

@Suppress("ObjectPropertyName")
private var _Strongbox: ImageVector? = null
