package org.violet.uiKit.ripple.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcHeartEmpty: ImageVector
    get() {
        if (HeartEmpty != null) {
            return HeartEmpty!!
        }
        HeartEmpty = ImageVector.Builder(
            name = "IcHeartEmpty",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.624f, 4.424f)
                curveTo(3.965f, 5.182f, 2.75f, 6.986f, 2.75f, 9.137f)
                curveTo(2.75f, 11.334f, 3.65f, 13.028f, 4.938f, 14.48f)
                curveTo(6.001f, 15.676f, 7.287f, 16.668f, 8.541f, 17.634f)
                curveTo(8.84f, 17.864f, 9.135f, 18.093f, 9.426f, 18.322f)
                curveTo(9.952f, 18.737f, 10.421f, 19.1f, 10.874f, 19.365f)
                curveTo(11.327f, 19.63f, 11.69f, 19.75f, 12f, 19.75f)
                curveTo(12.31f, 19.75f, 12.674f, 19.63f, 13.126f, 19.365f)
                curveTo(13.579f, 19.1f, 14.048f, 18.737f, 14.574f, 18.322f)
                curveTo(14.865f, 18.092f, 15.16f, 17.863f, 15.459f, 17.635f)
                curveTo(16.713f, 16.667f, 17.999f, 15.676f, 19.062f, 14.48f)
                curveTo(20.351f, 13.028f, 21.25f, 11.334f, 21.25f, 9.137f)
                curveTo(21.25f, 6.987f, 20.035f, 5.182f, 18.376f, 4.424f)
                curveTo(16.764f, 3.687f, 14.598f, 3.882f, 12.54f, 6.021f)
                curveTo(12.47f, 6.093f, 12.386f, 6.151f, 12.293f, 6.19f)
                curveTo(12.201f, 6.23f, 12.101f, 6.25f, 12f, 6.25f)
                curveTo(11.899f, 6.25f, 11.799f, 6.23f, 11.707f, 6.19f)
                curveTo(11.614f, 6.151f, 11.53f, 6.093f, 11.46f, 6.021f)
                curveTo(9.402f, 3.882f, 7.236f, 3.687f, 5.624f, 4.424f)
                close()
                moveTo(12f, 4.46f)
                curveTo(9.688f, 2.39f, 7.099f, 2.1f, 5f, 3.059f)
                curveTo(2.786f, 4.074f, 1.25f, 6.426f, 1.25f, 9.138f)
                curveTo(1.25f, 11.803f, 2.36f, 13.837f, 3.817f, 15.477f)
                curveTo(4.983f, 16.79f, 6.41f, 17.889f, 7.671f, 18.859f)
                curveTo(7.958f, 19.079f, 8.233f, 19.293f, 8.497f, 19.501f)
                curveTo(9.01f, 19.905f, 9.56f, 20.335f, 10.117f, 20.661f)
                curveTo(10.674f, 20.987f, 11.31f, 21.251f, 12f, 21.251f)
                curveTo(12.69f, 21.251f, 13.326f, 20.986f, 13.883f, 20.661f)
                curveTo(14.441f, 20.335f, 14.99f, 19.905f, 15.503f, 19.501f)
                curveTo(15.767f, 19.293f, 16.042f, 19.079f, 16.329f, 18.859f)
                curveTo(17.589f, 17.889f, 19.017f, 16.789f, 20.183f, 15.477f)
                curveTo(21.64f, 13.837f, 22.75f, 11.803f, 22.75f, 9.138f)
                curveTo(22.75f, 6.426f, 21.215f, 4.074f, 19f, 3.061f)
                curveTo(16.901f, 2.101f, 14.312f, 2.391f, 12f, 4.46f)
                close()
            }
        }.build()

        return HeartEmpty!!
    }

private var HeartEmpty: ImageVector? = null