package org.violet.violetapp.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Invoices: ImageVector
    get() {
        if (MoneyTick != null) {
            return MoneyTick!!
        }
        MoneyTick = ImageVector.Builder(
            name = "MoneyTick",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFAAAAAA)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(8.866f, 20.75f)
                horizontalLineTo(17f)
                curveTo(20.65f, 20.75f, 22.75f, 18.65f, 22.75f, 15f)
                verticalLineTo(9f)
                curveTo(22.75f, 5.35f, 20.65f, 3.25f, 17f, 3.25f)
                horizontalLineTo(7f)
                curveTo(3.35f, 3.25f, 1.25f, 5.35f, 1.25f, 9f)
                verticalLineTo(15.087f)
                curveTo(0.623f, 15.892f, 0.25f, 16.903f, 0.25f, 18f)
                curveTo(0.25f, 18.87f, 0.49f, 19.72f, 0.94f, 20.44f)
                curveTo(1.78f, 21.87f, 3.34f, 22.75f, 5f, 22.75f)
                curveTo(6.54f, 22.75f, 7.993f, 21.993f, 8.866f, 20.75f)
                close()
                moveTo(9.581f, 19.25f)
                horizontalLineTo(17f)
                curveTo(19.86f, 19.25f, 21.25f, 17.86f, 21.25f, 15f)
                verticalLineTo(9f)
                curveTo(21.25f, 6.14f, 19.86f, 4.75f, 17f, 4.75f)
                horizontalLineTo(7f)
                curveTo(4.14f, 4.75f, 2.75f, 6.14f, 2.75f, 9f)
                verticalLineTo(13.817f)
                curveTo(3.42f, 13.455f, 4.186f, 13.25f, 5f, 13.25f)
                curveTo(7.62f, 13.25f, 9.75f, 15.38f, 9.75f, 18f)
                curveTo(9.75f, 18.425f, 9.693f, 18.846f, 9.581f, 19.25f)
                close()
                moveTo(12f, 15.25f)
                curveTo(10.21f, 15.25f, 8.75f, 13.79f, 8.75f, 12f)
                curveTo(8.75f, 10.21f, 10.21f, 8.75f, 12f, 8.75f)
                curveTo(13.79f, 8.75f, 15.25f, 10.21f, 15.25f, 12f)
                curveTo(15.25f, 13.79f, 13.79f, 15.25f, 12f, 15.25f)
                close()
                moveTo(12f, 10.25f)
                curveTo(11.04f, 10.25f, 10.25f, 11.04f, 10.25f, 12f)
                curveTo(10.25f, 12.96f, 11.04f, 13.75f, 12f, 13.75f)
                curveTo(12.96f, 13.75f, 13.75f, 12.96f, 13.75f, 12f)
                curveTo(13.75f, 11.04f, 12.96f, 10.25f, 12f, 10.25f)
                close()
                moveTo(18.5f, 15.25f)
                curveTo(18.09f, 15.25f, 17.75f, 14.91f, 17.75f, 14.5f)
                verticalLineTo(9.5f)
                curveTo(17.75f, 9.09f, 18.09f, 8.75f, 18.5f, 8.75f)
                curveTo(18.91f, 8.75f, 19.25f, 9.09f, 19.25f, 9.5f)
                verticalLineTo(14.5f)
                curveTo(19.25f, 14.91f, 18.91f, 15.25f, 18.5f, 15.25f)
                close()
                moveTo(1.75f, 18f)
                curveTo(1.75f, 16.21f, 3.21f, 14.75f, 5f, 14.75f)
                curveTo(6.79f, 14.75f, 8.25f, 16.21f, 8.25f, 18f)
                curveTo(8.25f, 18.6f, 8.09f, 19.17f, 7.78f, 19.68f)
                curveTo(7.2f, 20.65f, 6.13f, 21.25f, 5f, 21.25f)
                curveTo(3.87f, 21.25f, 2.8f, 20.65f, 2.22f, 19.67f)
                curveTo(1.91f, 19.17f, 1.75f, 18.59f, 1.75f, 18f)
                close()
                moveTo(4.43f, 19.74f)
                curveTo(4.24f, 19.74f, 4.05f, 19.67f, 3.9f, 19.52f)
                lineTo(2.91f, 18.53f)
                curveTo(2.62f, 18.24f, 2.62f, 17.76f, 2.91f, 17.47f)
                curveTo(3.2f, 17.18f, 3.68f, 17.18f, 3.97f, 17.47f)
                lineTo(4.45f, 17.95f)
                lineTo(6.05f, 16.47f)
                curveTo(6.35f, 16.19f, 6.83f, 16.21f, 7.11f, 16.51f)
                curveTo(7.39f, 16.81f, 7.37f, 17.29f, 7.07f, 17.57f)
                lineTo(4.94f, 19.54f)
                curveTo(4.79f, 19.67f, 4.61f, 19.74f, 4.43f, 19.74f)
                close()
            }
        }.build()

        return MoneyTick!!
    }


private var MoneyTick: ImageVector? = null
