import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import coil3.PlatformContext
import org.koin.core.context.startKoin
import org.violet.violetapp.di.configureModules

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        configureModules(PlatformContext.INSTANCE)
    }
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
}