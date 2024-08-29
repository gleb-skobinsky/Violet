package org.violet.violetapp.common.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char

val VioletAppDateFormat = LocalDateTime.Format {
    dayOfMonth(); char('.'); monthNumber(); char('.'); year()
    char(' ')
    time(LocalTime.Formats.ISO)
}

val TransactionDateFormat = LocalDateTime.Format {
    dayOfMonth(); char('.'); monthNumber(); char('.'); year()
}