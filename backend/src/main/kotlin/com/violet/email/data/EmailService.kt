package com.violet.email.data

interface EmailService {
    suspend fun sendEmail(data: EmailData): Boolean

    suspend fun sendEmail2(data: EmailData): Boolean
}