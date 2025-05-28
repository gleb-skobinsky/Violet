package org.violet.violetapp.common.utils

enum class Platform {
    Android,
    IOS,
    Web;

    val isAndroid get() = this == Android
    val isIOS get() = this == IOS
    val isWeb get() = this == Web
}

expect val platform: Platform