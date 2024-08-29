package org.violet.violetapp.init

import org.violet.violetapp.init.presentation.InitStateController
import org.koin.dsl.module

val initModule = module {
    single { InitStateController() }
}