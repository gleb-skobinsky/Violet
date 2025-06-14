package org.violet.violetapp.di

import org.koin.core.context.startKoin

@Suppress("Unused") // used in swift
fun startIosKoin() {
    startKoin {
        configureModules()
    }
}