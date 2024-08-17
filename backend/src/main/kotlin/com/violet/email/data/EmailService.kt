package com.violet.email.data

interface EmailService {
    suspend fun sendEmail(data: EmailData): Boolean
}