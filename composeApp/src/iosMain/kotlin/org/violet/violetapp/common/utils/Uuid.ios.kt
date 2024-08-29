package org.violet.violetapp.org.violet.violetapp.common.utils

import platform.Foundation.NSUUID

actual fun uuid() = NSUUID.UUID().UUIDString()