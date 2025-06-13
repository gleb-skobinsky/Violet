package org.violet.violetapp

import App
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import org.violet.violetapp.di.configureModules

fun main() = application {
    startKoin {
        configureModules()
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "VioletApp",
    ) {
        App()
    }
}