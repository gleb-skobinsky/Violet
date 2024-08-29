package org.violet.violetapp.common.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(onBack: () -> Unit)