package org.violet.violetapp.previews

import VioletAppTheme
import ProvideNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.violet.violetapp.common.navigation.KMPNavigatorImpl

@Composable
internal fun PreviewWrapper(
    content: @Composable () -> Unit
) {
    VioletAppTheme {
        ProvideNavigator(KMPNavigatorImpl(rememberNavController())) {
            content()
        }
    }
}