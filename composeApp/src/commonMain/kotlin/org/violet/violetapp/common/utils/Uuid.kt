package org.violet.violetapp.common.utils

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun uuid(): String = Uuid.random().toString()