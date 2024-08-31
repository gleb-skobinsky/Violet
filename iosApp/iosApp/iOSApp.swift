import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        StartIosKoinKt.startIosKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
