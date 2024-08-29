package org.violet.violetapp.common.utils

val Any.QualifiedName: String
    get() = this::class.qualifiedName.orEmpty()