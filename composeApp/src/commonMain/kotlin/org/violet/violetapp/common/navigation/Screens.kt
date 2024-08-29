package org.violet.violetapp.common.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import org.violet.violetapp.common.icons.History
import org.violet.violetapp.common.icons.Home
import org.violet.violetapp.common.icons.Shop

@Serializable
sealed interface Screens {

    @Serializable
    data object LoginScreen : Screens

    @Serializable
    data object HomeScreen : Screens

    @Serializable
    data object InvoicesScreen : Screens

    @Serializable
    data object TransactionsScreen : Screens

    @Serializable
    data object SignupScreen : Screens

    @Serializable
    data class ForgotPasswordScreen(val email: String? = null) : Screens

    @Serializable
    data object FeedScreen : Screens

    @Serializable
    data object ProfileScreen : Screens
}

enum class BottomBarTab(
    val screen: Screens,
    val title: String,
    val hasNavBar: Boolean,
    val icon: ImageVector
) {
    HomeTab(
        screen = Screens.HomeScreen,
        title = "Home",
        hasNavBar = true,
        icon = Home
    ),
    ShowCaseTab(
        screen = Screens.FeedScreen,
        title = "Shop",
        hasNavBar = false,
        icon = Shop
    ),
    HistoryTab(
        screen = Screens.ProfileScreen,
        title = "History",
        hasNavBar = true,
        icon = History
    )
}
