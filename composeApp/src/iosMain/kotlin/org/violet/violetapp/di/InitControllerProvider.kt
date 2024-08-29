package org.violet.violetapp.org.violet.violetapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.violet.violetapp.init.presentation.InitStateController

@Suppress("Unused") // used in swift
object InitControllerProvider : KoinComponent {
    val controller: InitStateController
        get() = get()
}