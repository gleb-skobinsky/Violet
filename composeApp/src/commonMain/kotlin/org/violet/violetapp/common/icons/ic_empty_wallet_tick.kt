package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val EmptyWalletTick: ImageVector
    get() {
        if (_EmptyWalletTick != null) {
            return _EmptyWalletTick!!
        }
        _EmptyWalletTick = ImageVector.Builder(
            name = "EmptyWalletTick",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = CommonIconColor) {
                moveTo(17.74f, 22.75f)
                horizontalLineTo(7.63f)
                curveTo(7.31f, 22.75f, 7.03f, 22.55f, 6.92f, 22.26f)
                curveTo(6.81f, 21.96f, 6.9f, 21.63f, 7.14f, 21.43f)
                curveTo(7.38f, 21.23f, 7.6f, 20.97f, 7.76f, 20.69f)
                curveTo(8.08f, 20.18f, 8.24f, 19.6f, 8.24f, 19.01f)
                curveTo(8.24f, 17.22f, 6.78f, 15.76f, 4.99f, 15.76f)
                curveTo(4.25f, 15.76f, 3.55f, 16.01f, 2.96f, 16.48f)
                curveTo(2.74f, 16.66f, 2.43f, 16.69f, 2.17f, 16.57f)
                curveTo(1.91f, 16.45f, 1.75f, 16.18f, 1.75f, 15.89f)
                verticalLineTo(11.52f)
                curveTo(1.75f, 9.03f, 3.77f, 7.01f, 6.26f, 7.01f)
                horizontalLineTo(17.74f)
                curveTo(20.23f, 7.01f, 22.25f, 9.03f, 22.25f, 11.52f)
                verticalLineTo(12.96f)
                curveTo(22.25f, 13.37f, 21.91f, 13.71f, 21.5f, 13.71f)
                horizontalLineTo(19.48f)
                curveTo(19.13f, 13.71f, 18.81f, 13.84f, 18.58f, 14.08f)
                lineTo(18.57f, 14.09f)
                curveTo(18.29f, 14.36f, 18.16f, 14.73f, 18.19f, 15.11f)
                curveTo(18.25f, 15.77f, 18.88f, 16.3f, 19.6f, 16.3f)
                horizontalLineTo(21.5f)
                curveTo(21.91f, 16.3f, 22.25f, 16.64f, 22.25f, 17.05f)
                verticalLineTo(18.24f)
                curveTo(22.25f, 20.73f, 20.23f, 22.75f, 17.74f, 22.75f)
                close()
                moveTo(9.18f, 21.25f)
                horizontalLineTo(17.74f)
                curveTo(19.4f, 21.25f, 20.75f, 19.9f, 20.75f, 18.24f)
                verticalLineTo(17.8f)
                horizontalLineTo(19.6f)
                curveTo(18.09f, 17.8f, 16.81f, 16.68f, 16.69f, 15.24f)
                curveTo(16.61f, 14.42f, 16.91f, 13.61f, 17.51f, 13.02f)
                curveTo(18.03f, 12.49f, 18.73f, 12.2f, 19.48f, 12.2f)
                horizontalLineTo(20.75f)
                verticalLineTo(11.51f)
                curveTo(20.75f, 9.85f, 19.4f, 8.5f, 17.74f, 8.5f)
                horizontalLineTo(6.26f)
                curveTo(4.6f, 8.5f, 3.25f, 9.85f, 3.25f, 11.51f)
                verticalLineTo(14.59f)
                curveTo(3.81f, 14.37f, 4.4f, 14.25f, 5f, 14.25f)
                curveTo(7.62f, 14.25f, 9.75f, 16.38f, 9.75f, 19f)
                curveTo(9.75f, 19.79f, 9.55f, 20.57f, 9.18f, 21.25f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(2.5f, 13.16f)
                curveTo(2.09f, 13.16f, 1.75f, 12.82f, 1.75f, 12.41f)
                verticalLineTo(7.84f)
                curveTo(1.75f, 6.35f, 2.69f, 5f, 4.08f, 4.47f)
                lineTo(12.02f, 1.47f)
                curveTo(12.84f, 1.16f, 13.75f, 1.27f, 14.46f, 1.77f)
                curveTo(15.18f, 2.27f, 15.6f, 3.08f, 15.6f, 3.95f)
                verticalLineTo(7.75f)
                curveTo(15.6f, 8.16f, 15.26f, 8.5f, 14.85f, 8.5f)
                curveTo(14.44f, 8.5f, 14.1f, 8.16f, 14.1f, 7.75f)
                verticalLineTo(3.95f)
                curveTo(14.1f, 3.57f, 13.92f, 3.22f, 13.6f, 3f)
                curveTo(13.28f, 2.78f, 12.9f, 2.73f, 12.54f, 2.87f)
                lineTo(4.6f, 5.87f)
                curveTo(3.79f, 6.18f, 3.24f, 6.97f, 3.24f, 7.84f)
                verticalLineTo(12.41f)
                curveTo(3.25f, 12.83f, 2.91f, 13.16f, 2.5f, 13.16f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(19.6f, 17.8f)
                curveTo(18.09f, 17.8f, 16.81f, 16.68f, 16.69f, 15.24f)
                curveTo(16.61f, 14.41f, 16.91f, 13.6f, 17.51f, 13.01f)
                curveTo(18.02f, 12.49f, 18.72f, 12.2f, 19.47f, 12.2f)
                horizontalLineTo(21.55f)
                curveTo(22.54f, 12.23f, 23.3f, 13.01f, 23.3f, 13.97f)
                verticalLineTo(16.03f)
                curveTo(23.3f, 16.99f, 22.54f, 17.77f, 21.58f, 17.8f)
                horizontalLineTo(19.6f)
                close()
                moveTo(21.53f, 13.7f)
                horizontalLineTo(19.48f)
                curveTo(19.13f, 13.7f, 18.81f, 13.83f, 18.58f, 14.07f)
                curveTo(18.29f, 14.35f, 18.15f, 14.73f, 18.19f, 15.11f)
                curveTo(18.25f, 15.77f, 18.88f, 16.3f, 19.6f, 16.3f)
                horizontalLineTo(21.56f)
                curveTo(21.69f, 16.3f, 21.81f, 16.18f, 21.81f, 16.03f)
                verticalLineTo(13.97f)
                curveTo(21.81f, 13.82f, 21.69f, 13.71f, 21.53f, 13.7f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(14f, 12.75f)
                horizontalLineTo(7f)
                curveTo(6.59f, 12.75f, 6.25f, 12.41f, 6.25f, 12f)
                curveTo(6.25f, 11.59f, 6.59f, 11.25f, 7f, 11.25f)
                horizontalLineTo(14f)
                curveTo(14.41f, 11.25f, 14.75f, 11.59f, 14.75f, 12f)
                curveTo(14.75f, 12.41f, 14.41f, 12.75f, 14f, 12.75f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(5f, 23.75f)
                curveTo(3.34f, 23.75f, 1.78f, 22.87f, 0.94f, 21.44f)
                curveTo(0.49f, 20.72f, 0.25f, 19.87f, 0.25f, 19f)
                curveTo(0.25f, 17.54f, 0.9f, 16.19f, 2.03f, 15.29f)
                curveTo(2.87f, 14.62f, 3.93f, 14.25f, 5f, 14.25f)
                curveTo(7.62f, 14.25f, 9.75f, 16.38f, 9.75f, 19f)
                curveTo(9.75f, 19.87f, 9.51f, 20.72f, 9.06f, 21.45f)
                curveTo(8.82f, 21.87f, 8.49f, 22.25f, 8.11f, 22.57f)
                curveTo(7.28f, 23.33f, 6.17f, 23.75f, 5f, 23.75f)
                close()
                moveTo(5f, 15.75f)
                curveTo(4.26f, 15.75f, 3.56f, 16f, 2.97f, 16.47f)
                curveTo(2.2f, 17.08f, 1.75f, 18.01f, 1.75f, 19f)
                curveTo(1.75f, 19.59f, 1.91f, 20.17f, 2.22f, 20.67f)
                curveTo(2.81f, 21.67f, 3.85f, 22.25f, 5f, 22.25f)
                curveTo(5.79f, 22.25f, 6.55f, 21.96f, 7.13f, 21.44f)
                curveTo(7.39f, 21.22f, 7.61f, 20.96f, 7.77f, 20.68f)
                curveTo(8.09f, 20.17f, 8.25f, 19.59f, 8.25f, 19f)
                curveTo(8.25f, 17.21f, 6.79f, 15.75f, 5f, 15.75f)
                close()
            }
            path(fill = CommonIconColor) {
                moveTo(4.43f, 20.74f)
                curveTo(4.24f, 20.74f, 4.05f, 20.67f, 3.9f, 20.52f)
                lineTo(2.91f, 19.53f)
                curveTo(2.62f, 19.24f, 2.62f, 18.76f, 2.91f, 18.47f)
                curveTo(3.2f, 18.18f, 3.68f, 18.18f, 3.97f, 18.47f)
                lineTo(4.45f, 18.95f)
                lineTo(6.05f, 17.47f)
                curveTo(6.35f, 17.19f, 6.83f, 17.21f, 7.11f, 17.51f)
                curveTo(7.39f, 17.81f, 7.37f, 18.29f, 7.07f, 18.57f)
                lineTo(4.94f, 20.54f)
                curveTo(4.79f, 20.67f, 4.61f, 20.74f, 4.43f, 20.74f)
                close()
            }
        }.build()

        return _EmptyWalletTick!!
    }

@Suppress("ObjectPropertyName")
private var _EmptyWalletTick: ImageVector? = null
