package org.violet.violetapp.di

import coil3.PlatformContext
import org.koin.core.context.startKoin

@Suppress("Unused") // used in swift
fun startIosKoin() {
    startKoin {
        configureModules(PlatformContext.INSTANCE)
    }
}