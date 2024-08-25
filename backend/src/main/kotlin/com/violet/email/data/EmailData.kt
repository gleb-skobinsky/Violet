package com.violet.email.data

data class EmailData(
    val emailFrom: String,
    val emailTo: String,
    val subject: String,
    val message: String
)