package org.violet.violetapp.common.utils

enum class Platform {
    Android,
    IOS
}

val Platform.isAndroid
    get() = this == Platform.Android

val Platform.isIOS
    get() = this == Platform.IOS

expect val platform: Platform