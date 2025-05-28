package org.violet.violetapp.previews

import ProvideNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.violet.uiKit.theme.VioletTheme
import org.violet.violetapp.common.navigation.KMPNavigatorImpl

@Composable
internal fun PreviewWrapper(
    content: @Composable () -> Unit
) {
    VioletTheme {
        ProvideNavigator(KMPNavigatorImpl(rememberNavController())) {
            content()
        }
    }
}